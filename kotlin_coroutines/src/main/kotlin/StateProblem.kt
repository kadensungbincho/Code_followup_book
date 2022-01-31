import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import sun.net.NetworkServer

class UserDownloader(
    private val api: NetworkService,
) {
    private val users = mutableListOf<MyUser>()

    fun downloaded(): List<MyUser> = users.toList()

    suspend fun fetchUser(id: Int) {
        val newUser = api.fetchUser(id)
        users.add(newUser)
    }
}

class MyUser(val name: String)

interface NetworkService {
    suspend fun fetchUser(id: Int): MyUser
}

class FakeNetworkService: NetworkService {
    override suspend fun fetchUser(id: Int): MyUser {
        delay(2)
        return MyUser("User$id")
    }
}

//suspend fun main() {
//    val downloader = UserDownloader(FakeNetworkService())
//
//    coroutineScope {
//        repeat(1_000_000) {
//            launch {
//                downloader.fetchUser(it)
//            }
//        }
//    }
//
//    print(downloader.downloaded().size)
//}

suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) { action() }
            }
        }
    }

var counter = 0

//fun main() = runBlocking { massiveRun {
//    counter++ }
//    println(counter)
//}

//fun main() = runBlocking {
//    val lock = Any()
//
//    massiveRun {
//        synchronized(lock) { // blocking
//            counter++
//        } }
//    println(counter)
//}

private var counterAtomic = AtomicInteger()

//fun main() = runBlocking {
//    massiveRun {
//        counterAtomic.incrementAndGet()
//    }
//    println(counterAtomic.get())
//}

class UserDownloader1(
    private val api: NetworkService,
) {
    private val users = AtomicReference(listOf<MyUser>())

    fun downloaded(): List<MyUser> = users.get()

    suspend fun fetchUser(id: Int) {
        val newUser = api.fetchUser(id)
        users.getAndUpdate { it + newUser }
    }
}

class UserDownloader2(
    private val api: NetworkService,
) {
    private val users = mutableListOf<MyUser>()
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    suspend fun downloaded(): List<MyUser> =
        withContext(dispatcher) {
            users.toList()
        }

    suspend fun fetchUser(id: Int) =
        withContext(dispatcher) {
            val newUser = api.fetchUser(id)
            users.add(newUser)
        }
}

// fine-grained thread confinement
class UserDownloader3(
    private val api: NetworkService,
) {
    private val users = mutableListOf<MyUser>()
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    suspend fun downloaded(): List<MyUser> =
        withContext(dispatcher) {
            users.toList()
        }

    suspend fun fetchUser(id: Int) {
        val newUser = api.fetchUser(id)
        withContext(dispatcher) {
            users.add(newUser)
        }
    }
}

// mutex
val mutex = Mutex()
suspend fun delayAndPrint() {
    mutex.lock()
    delay(1000)
    println("Done")
    mutex.unlock()
}

//suspend fun main() = coroutineScope {
//    repeat(5) {
//        launch {
//            delayAndPrint()
//        }
//    }
//}

//fun main() = runBlocking {
//    massiveRun {
//        mutex.withLock { // suspending a coroutine instead of blocking a thread
//            counter++
//        }
//    }
//
//    println(counter)
//}

//suspend fun main() {
//    val mutex = Mutex()
//    println("Started")
//    mutex.withLock {
//        mutex.withLock {  // dead lock
//            println("Will never be printed")
//        }
//    }
//}

class MessagesRepository {
    private val messages = mutableListOf<String>()
    private val mutex = Mutex()

    suspend fun add(message: String) = mutex.withLock {
        delay(1000)
        messages.add(message)
    }
}

//suspend fun main() {
//    val repo = MessagesRepository()
//
//    val timeMillis = measureTimeMillis {
//        coroutineScope {
//            repeat(5) {
//                launch {
//                    repo.add("Message$it")
//                }
//            }
//        }
//    }
//    println(timeMillis) // took 5 seconds
//}


class MessagesRepository1 {
    private val messages = mutableListOf<String>()
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    suspend fun add(message: String) =
        withContext(dispatcher) {
            delay(1000)
            messages.add(message)
        }
}

suspend fun main() {
    val repo = MessagesRepository1()

    val timeMillis = measureTimeMillis {
        coroutineScope {
            repeat(5) {
                launch {
                    repo.add("Message$it")
                }
            }
        }
    }
    println(timeMillis) // took 1.058 seconds
}

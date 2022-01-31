import kotlin.coroutines.Continuation
import java.util.concurrent.Executors
import javax.xml.ws.Dispatch
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

//suspend fun main() = coroutineScope {
//    repeat(1000) {
//        launch(Dispatchers.IO) {
//            Thread.sleep(200)
//
//            val threadName = Thread.currentThread().name
//            println("Running on thread: $threadName")
//        }
//    }
//}

const val NUMBER_OF_THREADS = 20
val dispatcher = Executors
    .newFixedThreadPool(NUMBER_OF_THREADS)
    .asCoroutineDispatcher()

//suspend fun main() = coroutineScope {
//    val dispatcher = Executors
//        .newFixedThreadPool(5)
//        .asCoroutineDispatcher()
//
//    repeat(1000) {
//        launch(dispatcher) {
//            Thread.sleep(200)
//
//            val threadName = Thread.currentThread().name
//            println("Running on thread: $threadName")
//        }
//    }
//    dispatcher.close()
//}

suspend fun test() {
    val dispatcher = Dispatchers.IO
        .limitedParallelism(5)

    withContext(dispatcher) {
        Thread.sleep(200)
    }
}


var i = 0

//suspend fun main(): Unit = coroutineScope {
//    repeat(10_000) {
//        launch(Dispatchers.IO) {
//            i++
//        }
//    }
//    delay(1000)
//    println(i)
//}

//suspend fun main(): Unit = coroutineScope {
//    val dispatcher = Dispatchers.Default
//        .limitedParallelism(1)
//
//    repeat(10_000) {
//        launch(dispatcher) {
//            i++
//        }
//    }
//
//    delay(1000)
//    println(i)
//}

//suspend fun main(): Unit = coroutineScope {
//    val dispatcher = Dispatchers.Default
//        .limitedParallelism(1)
//
//    val job = Job()
//
//    repeat(5) {
//        launch(dispatcher + job) {
//            Thread.sleep(1000)
//        }
//    }
//    job.complete()
//    val time = measureTimeMillis { job.join() }
//    println("Took $time")
//}

@SinceKotlin("1.3")
public inline fun <T> Continuation<T>.resume(value: T): Unit =
    resumeWith(Result.success(value))


suspend fun main(): Unit =
    withContext(newSingleThreadContext("Name1")) {
        var continuation: Continuation<Unit>? = null

        launch(newSingleThreadContext("Name2"))  {
            delay(1000)
            continuation?.resume(Unit)
        }

        launch(Dispatchers.Unconfined) {
            println(Thread.currentThread().name)

            suspendCoroutine<Unit> { continuation = it}

            println(Thread.currentThread().name)

            delay(1000)

            println(Thread.currentThread().name)
        }
    }

/*
    1. When we are just suspending, it doesn't really matter how many threads we are using
    2. When we are blocking, the more threads we are using, the faster all those coroutines will be finished
    3. With CPU-intensive operations, Dispatchers.Default is the best option
    4. If we are dealing with a memory-intensive problem, more threads might lead to some improvement
 */


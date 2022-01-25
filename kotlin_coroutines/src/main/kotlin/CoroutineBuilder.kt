import kotlin.concurrent.thread
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    test7()
}

fun test1() {
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000L)
}

fun test2() {
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World!")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World!")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000L)
}

fun test3() {
    runBlocking {
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

fun test4() {
    Thread.sleep(1000L)
    println("World!")
    Thread.sleep(1000L)
    println("World!")
    Thread.sleep(1000L)
    println("World!")
    println("Hello,")
}

fun test5() {
    runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        delay(2000L)
    }
}

fun test6() {
    runBlocking {
        val resultDeferred: Deferred<Int> = GlobalScope.async {
            delay(1000L)
            42
        }

        val result = resultDeferred.await()
        println(result)
        println(resultDeferred.await())
    }
}

fun test7() {
    runBlocking {
        val res1 = GlobalScope.async {
            delay(1000L)
            "Text 1"
        }
        val res2 = GlobalScope.async {
            delay(1000L)
            "Text 2"
        }
        val res3 = GlobalScope.async {
            delay(1000L)
            "Text 3"
        }
        println(res1.await())
        println(res2.await())
        println(res3.await())
    }
}

/*
    - children inherit context from their parent (but can also overwrite it, it will be explained
        in the chapter Coroutine context),
    - a parent suspends until all the children are finished
    - when the parent is cancelled, its child coroutines are cancelled too
    - when a child raises an error, it destroys the parent as well

    This means that runBlocking will be used in different cases than other coroutines
 */

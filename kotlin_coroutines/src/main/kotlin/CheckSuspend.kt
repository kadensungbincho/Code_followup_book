package ch1

import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("Before")

//    suspendCoroutine<Unit> { continuation ->
//        println("Suspended")
//        Thread.sleep(1000)
//        continuation.resume(Unit)
//        println("Resumed")
//    }

    suspendCoroutine<Unit> { continuation ->
        invokeAfterSecond {
            continuation.resume(Unit)
        }
    }

    println("After")
}

fun invokeAfterSecond(operation: () -> Unit) {
    thread {
        Thread.sleep(1000)
        operation.invoke()
    }
}

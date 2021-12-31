package ch1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun generateConflicts() {
    var num = 0
    for (i in 1..1000) {
        thread {
            Thread.sleep(10)
            num += 1
        }
    }

    Thread.sleep(5000)
    println(num)
}

suspend fun generateConflictsWithCoroutine() {
    var num = 0
    coroutineScope {
        for (i in 1..1000) {
            launch {
                delay(10)
                num += 1
            }
        }
    }
    println(num)
}

fun properMutation() {
    val lock = Any()
    var num = 0
    for (i in 1..1000) {
        thread {
            Thread.sleep(10)
            synchronized(lock) {
                num += 1
            }
        }
    }
    Thread.sleep(1000)
    println(num)
}

suspend fun main(args: Array<String>) {
    // generateConflictsWithCoroutine()
    // properMutation()

    val list = mutableListOf(1, 2, 3)
    list.add(4)

    println(list)

    var name = "Marcin"
    var surname = "Moskata"
    //val fullName get() = "$name $surname"
}
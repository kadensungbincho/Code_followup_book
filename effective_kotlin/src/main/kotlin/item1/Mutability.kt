package item1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.properties.Delegates

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

interface Element {
    val active: Boolean
}

class ActualElement: Element {
    // val can be overrided with var
    override var active: Boolean = false
}

//fun smartCast() {
//    val name: String? = "kaden"
//    val surname: String = "cho"
//
//    val fullName:String? = "name"
//        get() = name?.let { "$it $surname"}
//
//
//    val fullName2: String? = name?.let{ "$it $surname" }
//
//    if (fullName != null) {
//        println(fullName.length)
//    }
//
//    if (fullName2 != null) {
//        println(fullName2.length)
//    }
//}


// Separation between mutable and read-only collections


inline fun <T, R> Iterable<T>.map(
    transformation: (T) -> R
): List<R> {
    val list = ArrayList<R>()
    for (elem in this) {
        list.add(transformation(elem))
    }
    return list
}

fun hackTheList() {
    val list = listOf(1, 2, 3)

    // Don't do this
    if (list is MutableList) {
        list.add(4)
    }
}

fun properCastChange() {
    val list = listOf(1, 2, 3)

    val mutableList = list.toMutableList()
    mutableList.add(4)
}

/*
    - easier to reason about since their state stays the same
    - make it easier to parallelize the program
 */

fun mutationPoints() {
    val list1: MutableList<Int> = mutableListOf()
    var list2: List<Int> = listOf()

    list1.add(1)
    list2 = list2 + 1

    list1 += 1 // list1.plusAssign(1)
    list2 += 1 // list2 = list2.plus(1)
}

fun checkSync() {
    var list = listOf<Int>()

    for (i in 1..1000) {
        thread {
            list = list + i
        }
    }

    Thread.sleep(1000)
    println(list.size)
}

fun useDelegates() {
    var names by Delegates.observable(listOf<String>()) {
        _, old, new ->
            println("Names changed from $old to $new")
    }

    names += "kaden"

    names += "cho"
}

// In short, using mutable collections is a slightly faster option,
// but using a mutable property instead gives us more control over how the object is changing

suspend fun main(args: Array<String>) {
    // generateConflictsWithCoroutine()
    // properMutation()

//    val list = mutableListOf(1, 2, 3)
//    list.add(4)

//    println(list)

//    var name = "Marcin"
//    var surname = "Moskata"
    //val fullName get() = "$name $surname"


//    smartCast()
//    checkSync()

    useDelegates()
}
package ch7_efficiency

inline fun repeat(times: Int, action: (Int) -> Unit) {
    for (index in 0 until times) {
        action(index)
    }
}

/*
    - A type argument can be reified
    - Functions with functional parameters are faster when they are inline
    - Non-local return is allowed


 */

// Function calls are replaced with its body, so type parameters uses can be replaced with
// type arguments, by using the reified modifier

inline fun <reified T> printTypeName() {
    print(T::class.simpleName)
}

//fun <T> printA(list: List<T>) {
//    if (list is List<Int>) {
//        println("Int List!")
//    }
//}

fun main() {
    printTypeName<Int>()
    printTypeName<Char>()

    val my = listOf(1, 2, 3)

    println(my is List<Int>)
}


inline fun printThree() {





    print(3)
}
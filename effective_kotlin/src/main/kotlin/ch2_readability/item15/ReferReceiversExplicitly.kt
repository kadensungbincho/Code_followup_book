package ch2_readability.item15

fun <T: Comparable<T>> List<T>.quickSort(): List<T> {
    if (size < 2) return this
    val pivot = first()
    val (smaller, bigger) = drop(1)
        .partition { it < pivot }

    return smaller.quickSort() + pivot + bigger.quickSort()
}

fun <T: Comparable<T>> List<T>.quickSort1(): List<T> {
    if (this.size < 2) return this
    val pivot = this.first()
    val (smaller, bigger) = this.drop(1)
        .partition { it < pivot }

    return smaller.quickSort1() + pivot + bigger.quickSort1()
}

class Node1(val name: String) {

    fun makeChild(childName: String) =
        create("$name.$childName")
            .apply { print("Created ${name}") }

    fun create(name: String): Node1? = Node1(name)
}

class Node2(val name: String) {

//    fun makeChild(childName: String) =
//        create("$name.$childName")
//            .apply { print("Created ${this.name}") } // compilation error

    fun create(name: String): Node2? = Node2(name)
}

class Node3(val name: String) {

    fun makeChild(childName: String) =
        create("$name.$childName")
            .apply { print("Created ${this?.name}") }

    fun create(name: String): Node3? = Node3(name)
}

class Node4(val name: String) {

    fun makeChild(childName: String) =
        create("$name.$childName")
            .also { print("Created ${it?.name}") }

    fun create(name: String): Node4? = Node4(name)
}


class Node5(val name: String) {

    fun makeChild(childName: String) =
        create("$name.$childName")
            .apply { print("Created ${this?.name} in ${this@Node5.name}") }

    fun create(name: String): Node5? = Node5(name)
}
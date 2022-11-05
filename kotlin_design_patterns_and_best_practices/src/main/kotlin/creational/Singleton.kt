fun main() {

    // val test = listOf()
    // val test1 = listOf()

    // println(test == test1ß)

    printMovies(emptyList())

}


//object NoMoviesList: List<String> {
//    override val size = 0
//    override fun containsAll(elements: Collection<String>): Boolean {
//        return false
//    }
//
//}

fun printMovies(movies: List<String>) {
    for (m in movies) {
        println(m)
    }
}

object Logger {
    init {
        println("Createds")
    }
}

package structural

import java.lang.IllegalStateException

interface StarTrekRepository {
    operator fun get(starshipName: String): String
    operator fun set(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository : StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean")

    override fun get(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    override fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository[starshipName]
    }
}

class ValidatingAdd(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val maxNameLength = 15
    override fun set(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }

        repository[starshipName] = captainName
    }
}


fun main() {
    val starTrekRepository = DefaultStarTrekRepository()
    val withValidating = ValidatingAdd(starTrekRepository)
    val withLoggingValidating = LoggingGetCaptain(withValidating)

    withLoggingValidating["USS Enterprise"]

    try {
        withLoggingValidating["USS Voyager"] = "Janeway"
    } catch (e: IllegalStateException) {
        println(e)
    }

    println(withLoggingValidating is LoggingGetCaptain)
    println(withLoggingValidating is StarTrekRepository)
//    println(withLoggingValidating is ValidatingAdd)
//    println(withLoggingValidating is DefaultStarTrekRepository)
}

package item7

/*
    - Return a null or a sealed class indicating failure
    - Throw an exception

    Exceptions should not be used as a standard way to pass information
    All exceptions indicate incorrect, special situations and should be treated
    this way. We should use exceptions only for exceptional condition

    - all exceptions are unchecked
    - exceptions are designed for exceptional circumstances, little incentive for JVM implementers to make them fast
    - placing try-catch block inhibits certain optimizations that compiler might otherwise perform
 */
/*

    inline fun <reified T> String.readObjectOrNull(): T? {
        // ...

        if (incorrectSign) {
            return null
        }

        return result
    }

    inline fun <reified T> String.readObject(): Result<T> {
        // ...
        if (incorrectSign) {
            return Failure(JsonParsingException())
        }

        return Success(result)
    }

    sealed class Result<out T>
    class Success<out T>(val result: T): Result<T>()
    class Failure(val throwable: Throwable): Result<Nothing>()

     class JsonParsingException: Exception()


 */
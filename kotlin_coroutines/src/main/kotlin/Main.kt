import java.math.BigInteger

fun main(args: Array<String>) {
    val seq = sequence {
        println("Generating first")
        yield(1)
        println("Generating second")
        yield(2)
        println("Generating third")
        yield(3)
        println("Done")
    }

    for (num in seq) {
        println("Next number is $num")
    }

    val fibonacci: Sequence<BigInteger> = sequence {
        var first = 0.toBigInteger()
        var second = 1.toBigInteger()

        while (true) {
            yield(first)
            val temp = first
            first += second
            second = temp
        }
    }

    print(fibonacci.take(10).toList())
}


package ch1_safty

// When we tighten a variable's scope, it is easier to keep our programs simple
// to track and manage

fun generateWrongPrimes1() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 }

        var prime: Int
        while (true) {
            prime = numbers.first()
            yield(prime)
            numbers = numbers.drop(1)
                .filter { it % prime != 0 }
        }
    }

    print(primes.take(10).toList())
}

fun generatePrimes() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 }

        while (true) {
            val prime = numbers.first()
            yield(prime)
            numbers = numbers.drop(1)
                .filter { it % prime != 0  }

        }
    }

    println(primes.take(10).toList())
}
fun main() {
//    generateWrongPrimes1()

    generatePrimes()
}
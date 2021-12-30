import org.springframework.core.io.ClassPathResource
import kotlin.random.Random

class BankAccount {
    var balance = 0.0
        private set

    fun deposit(depositAmount: Double) {
        balance += depositAmount
    }

    @Throws(InsufficientFunds::class)
    fun withdraw(withdrawAmount: Double) {
        if (balance < withdrawAmount) {
            throw InsufficientFunds()
        }
        balance -= withdrawAmount
    }
}

class InsufficientFunds : Exception()

fun main(args: Array<String>) {
    val account = BankAccount()
    println(account.balance)
    account.deposit(100.0)
    println(account.balance)
}
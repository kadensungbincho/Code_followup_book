import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

//fun main() {
//    notValidSupervisorScopeSample()
//}

fun withContextSample() {
    runBlocking(CoroutineName("Parent")) {
        log("Before")

        withContext(CoroutineName("Child 1")) {
            delay(1000)
            log("Hello 1")
        }

        withContext(CoroutineName("Child 2")) {
            delay(1000)
            log("Hello 2")
        }

        log("After")
    }
}

fun CoroutineScope.log(text: String) {
    val name = this.coroutineContext[CoroutineName]?.name
    println("[$name] $text")
}

fun supervisorScopeSample() {
    runBlocking {
        println("Before")

        supervisorScope {
            launch {
                delay(1000)
                throw Error()
            }

            launch {
                delay(2000)
                println("Done")
            }
        }

        println("After")
    }
}

fun notValidSupervisorScopeSample() {
    runBlocking {
        println("Before")

        withContext(SupervisorJob()) {
            launch {
                delay(1000)
                throw Error()
            }

            launch {
                delay(2000)
                println("Done")
            }
        }
        println("After")
    }
}

suspend fun withTimeoutSample(): Int = withTimeout(1500) {
    delay(1000)
    println("Still thinking")
    delay(1000)
    println("Done!")
    42
}

suspend fun main(): Unit = coroutineScope {
    try {
        withTimeoutSample()
    } catch (e: TimeoutCancellationException) {
        println("Cancelled")
    }
    delay(1000)
}

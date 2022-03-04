import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
suspend fun main() {
    println(scope.coroutineContext.job.key)
    scope.launch {
        println(this.coroutineContext.job.key)
        delay(50)
        println("first launch")
    }
    scope.launch {
        println(this.coroutineContext.job.key)
        throw Error()
    }
    // delay(100)
    scope.coroutineContext.job.children.forEach { it.join() }
}

suspend fun testScope1() {
    coroutineScope {
        val job = Job()
        launch(job) {
            try {
                delay(200)
                println("Job is done")
            } finally {
                println("Finally")
                launch {
                    println("Will not be printed")
                }
                delay(100)
                println("Will not be printed")
            }
        }
        delay(100)
        job.cancelAndJoin()
        println("Cancel done")
    }
}

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    createFlow1()
}

suspend fun createFlow1() {
    flowOf(1, 2, 3, 4, 5)
        .collect { print(it) }
}

suspend fun functionToFlow() {
    val function = suspend {
        delay(1000)
        "UserName"
    }

    function.asFlow()
        .collect() { print(it) }
}

suspend fun getUserName1(): String {
    delay(1000)
    return "UserName"
}

suspend fun functionToFlow2() {
    ::getUserName1
        .asFlow()
        .collect { println(it) }
}

fun makeFlow(): Flow<Int> = flow {
    repeat(3) {
        num ->
        delay(1000)
        emit(num)
    }
}

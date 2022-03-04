import kotlin.random.Random
import kotlin.system.measureNanoTime
import org.apache.commons.lang3.RandomStringUtils

private const val MID_REGEX = "^u[0-9a-f]{32}"

fun main() {
    val mids = generateSequence { RandomStringUtils.randomAlphanumeric(Random.nextInt(32)) }
        .take(100000)

    val total = measureNanoTime {
        mids.forEach {
            println(MID_REGEX.toRegex().matches(it))
        }
    }

    println("total seconds: $total")
}

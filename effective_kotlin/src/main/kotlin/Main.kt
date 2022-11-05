

import org.apache.commons.lang3.RandomStringUtils

const val BASE_COIN_REGEX = "^[a-z][a-z0-9]{1,4}"
const val CONTRACT_ID_REGEX = "^[a-f0-9]{8}"
const val TOKEN_ID_REGEX = "^[a-f0-9]{16}"
const val TOKEN_TYPE_REGEX = "^[a-f0-9]{8}"
const val TOKEN_INDEX_REGEX = "^[a-f0-9]{8}"

fun randomShortAlphanumeric(count: Int = 8): String = RandomStringUtils.randomAlphanumeric(count)

fun validateBaseCoinId(contractId: String): Boolean {
    return BASE_COIN_REGEX.toRegex().matches(contractId)
}

fun validateContractId(contractId: String): Boolean {
    return CONTRACT_ID_REGEX.toRegex().matches(contractId)
}

fun validateTokenType(tokenType: String): Boolean {
    return TOKEN_TYPE_REGEX.toRegex().matches(tokenType)
}

fun validateTokenIndex(tokenIndex: String): Boolean {
    return TOKEN_INDEX_REGEX.toRegex().matches(tokenIndex)
}

fun validateTokenId(tokenId: String): Boolean {
    return TOKEN_ID_REGEX.toRegex().matches(tokenId)
}



fun main(args: Array<String>) {

    val times = 100
    val samples = generateSequence {
        randomShortAlphanumeric(30)
    }.take(times).toList()

    //
    val timeMills = measureTimeMillis {
        for (sample in samples) {
            validateBaseCoinId(sample)
        }
    }
    println(timeMills)
}
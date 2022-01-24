fun main() {

}

suspend fun printUser(token: String) {
    println("Before")
    val userId = getUserId(token)
    println("Get userId: $userId")
    val userName = getUserName(userId, token)
    println(User(userId, userName))
    println("After")
}

suspend fun getUserId(token: String): String {
    return token + "id"
}

suspend fun getUserName(userId: String, token: String): String {
    return userId + token
}

data class User(
    val userId: String,
    val userName: String,
)

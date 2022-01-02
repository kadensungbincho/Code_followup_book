package item3

/*

    val users: List<List<User>> = UserRepo().groupedUsers!!
        .map { it!!.filterNotNull() }

    This is why instead of being treated as nullable by default,
    a type that comes from Java and has unknown nullability is a
    special type in Kotlin. It is called a platform type

    so...

    val user1 = repo.user
    val user2: User = repo.user
    val user3: User? = repo.user

    even if a function does not return null now, that doesn't mean that
    it won't change in the future


 */

/*
    fun platformType() {
        val value = JavaClass().value

        ..

        println(value.length) // NPE
    }

    fun statedType() {
        val value: String = JavaClass().value // NPE

        println(value.lengh)

    }
 */
fun main() {

}
package item5

/*
    - require
    - check
    - assert block
    - Elvis operator with return or throw
 */


/*
    - Nullability and smart casting

    public inline fun require(value: Boolean): Unit {
        contract {
            returns() implies value
        }
        require(value) { "Failed requirement." }
    }

 */

class Person(val email: String?)

fun sendEmail(person: Person, message: String) {
    require(person.email != null) // smart casting
    val email: String = person.email
}

fun log(log: String) {
    println(log)
}

fun sendEmail1(person: Person, text: String) {
    val email: String = person.email ?: run {
        log("Email not sent")
        return
    }
}
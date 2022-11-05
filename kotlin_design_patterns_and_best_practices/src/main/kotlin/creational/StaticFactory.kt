package creational

class Server1 private constructor(port: Long) {
    init {
        println("Server started on port $port")
    }

    companion object {
        fun withPort(port: Long): Server1 {
            return Server1(port)
        }
    }
}


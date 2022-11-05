package creational

import java.util.Properties

interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class PropertyImpl (
    override val name: String,
    override val value: Any,
) : Property

data class ServerConfigurationImpl (
    override val properties: List<Property>
) : ServerConfiguration

fun property(prop: String) : Property {
    val (name, value) = prop.split(":")
    return when (name) {
        "port" -> PropertyImpl(name, value.trim().toInt())
        "environment" -> PropertyImpl(name, value.trim())
        else -> throw RuntimeException("Unknown property: $name")
    }
}

data class IntProperty (
    override val name: String,
    override val value: Int
) : Property

data class StringProperty(
    override val name: String,
    override val value: Int
) : Property


fun main() {
    val portProperty = property("port: 8080")
    val environment = property("environment: production")

    val port: Int? = portProperty.value as? Int


}

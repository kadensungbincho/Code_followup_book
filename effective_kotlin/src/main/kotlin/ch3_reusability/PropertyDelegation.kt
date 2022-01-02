package ch3_reusability

import kotlin.reflect.KProperty

/*
    lazy, observable delegates
    View, Resource Binding, Dependency Injection, Data Binding


 */
//
//fun simpleDelegate() {
//    var token: String? = null
//        get() {
//            print("token returned value $field")
//            return field
//        }
//        set(value) {
//            print("token changed from $field to $value")
//            field = value
//        }
//}

private class LoggingProperty<T>(var value: T) {
    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T {
        print("${prop.name} returned value $value")
        return value
    }

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        val name = prop.name
        print("$name changed from $value to $newValue")
        value = newValue
    }
}

var token: String? by LoggingProperty(null)
var attempts: Int by LoggingProperty(0)

/*

    @JvmField
    private val `token$delegate` =
        LoggingProperty<String?>(null)
    var token:String?
        get() = `token$delegate`.getValue(this, ::token)
        set(value) {
        `token$delegate`.setValue(this, ::token, value)
    }
 */
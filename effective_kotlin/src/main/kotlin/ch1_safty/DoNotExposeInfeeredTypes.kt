package ch1_safty

open class Animal
class Zebra: Animal()

fun cast() {
//    var animal = Zebra()
//    animal = Animal() // Type mismatch

    var animal: Animal = Zebra()
    animal = Animal()
}

open class Car

interface CarFactory {
    fun produce(): Car
}
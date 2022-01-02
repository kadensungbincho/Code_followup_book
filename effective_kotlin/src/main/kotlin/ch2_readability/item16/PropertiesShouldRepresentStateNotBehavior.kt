package ch2_readability.item16

/*
    // Don't do this
    val Tree<Int>.sum: Int
        get() = when(this) {
            is Leaf -> value
            is Node -> left.sum + right.sum
        }
    this should not be a property, this should be a function


    - Operation is computationally expensive or has computational complexity higher than O(1)
    - It involves business logic (how the application acts)
    - It is not deterministic
    - It is a conversion, such as Int.toDouble()
    - Getters should not change property state
 */


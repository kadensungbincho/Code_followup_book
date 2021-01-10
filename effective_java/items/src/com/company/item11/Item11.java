package com.company.item11;

public class Item11 {
    /*
        Always override hashCode when you override equals

        - You must override hashCode in every class that overrides equals
            not to violate the general contract for hashCode.

            - when the hashCode is invoked on an object repeatedly during an
                execution of an application, it must consistently return the same
                value, provided no information used in equals comparisons is modified.
            - If two objects are equal according to the equals(Object) method, then calling
                hashCode on the two objects must produce the same integer result
            - If two objects are unequal according to the equals(Object) method, it is not
                required that calling hashCode on each of the objects must produce distinct results.
                However, the programmer should be aware that producing distinct results for unequal
                objects may improve the performance of hash tables.

        - The key provision that is violated when you fail to override hashCode is the second one:
            equal objects must have equal

        - If you believe that most objects of this type will be used as hash keys, then you should
            calculate the hash code when the instance is created. Some care is required to ensure that the
            class remains thread-safe in the presence of a lazily initiated field.
     */

}

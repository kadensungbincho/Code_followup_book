package com.company.item17;

public class Item17 {
    /*
        Minimize Mutability

        - 1. Don't provide methods that modify the object's state
        - 2. Ensure that the class can't be extended
        - 3. Make all fields final
        - 4. Make all fields private
        - 5. Ensure exclusive access to any mutable components

        - Immutable objects are inherently thread-safe; they require no synchronization
        - Immutable objects can be share freely

        - Not only can you share immutable objects, but they can share their internals
        - Immutable objects make great building  blocks for other objects
        - The major disadvantage of immutable classes is that they require a separate object
             for each distinct value
     */

    static class Complex {
        private final double re;
        private final double im;

        private Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public static Complex valueOf(double re, double im) {
            return new Complex(re, im);
        }
    }
}

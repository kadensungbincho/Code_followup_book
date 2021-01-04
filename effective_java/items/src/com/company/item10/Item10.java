package com.company.item10;

import java.io.PipedOutputStream;
import java.util.Objects;

public class Item10 {
    /*
        - Each instance of the class is inherently unique.
        - There is not need for the class to provide a "logical equality" test
        - A superclass has already overridden equals, and the superclass behavior is appropriate for this class
        - The class is private or package-private, and you are certain that its equals method will never be invoked
     */

    /*
        - when is it appropriate to override equals?

        - It is when a class has a notion of logical equality that differs from mere object identity
            and a superclass has not already overridden equals.
        - value classes

        Properties
        - Reflexive: For any non-null reference value x, x.equals(x) must return true
        - Symmetric: For any non-null reference values x and y, x.equals(y) must return true
            if and only if y.equals(x) return true.
        - Transitive: For any non-null reference values x, y, x, if x.equals(y) returns true
            and y.equals(z) returns true, then x.equals(z) must return true.
        - Consistent: For any non-null reference values x and y, multiple invocations of x.equals(y) must consistently return true
            or consistently return false, provided no information used in equals comparisons is modified
        - For any non-null reference value x, x.equals(null) must return false.
     */
    // Symmetric
    public final class CaseInsensitiveString {
        private final String s;

        public CaseInsensitiveString(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
//            if (o instanceof CaseInsensitiveString) {
//                return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//            }
//
//            if (o instanceof  String) {
//                return s.equalsIgnoreCase((String) o);
//            }
//
//            return false;

            // the above has violation
            return o instanceof CaseInsensitiveString &&
                    ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
        }
    }
}

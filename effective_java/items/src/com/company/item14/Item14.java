package com.company.item14;

public class Item14 {
    /*
        Consider implementing Comparable

        - By implementing Comparable, you allow your class to inter-operate with all of
            the many generic algorithms and collection implementations that depend on this interface.

        - Writing a compareTo method is similar to writing an equals method, but there are a few key differences. Because
            the Comparable interface is parameterized, the compareTo method is statically typed, so you don't need to type
            check or cast its argument. If the argument is of the wrong type, the invocation won't even compile.

        - Use of the relational operators < and > in compareTo methods is verbose and error-prone and no longer recommended.
     */

}

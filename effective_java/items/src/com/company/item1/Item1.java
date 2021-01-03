package com.company.item1;

public class Item1 {
    public static void main(String[] args) {

    }

    /*
        A Static Factory Method
        - un`like constructors, they have names
        - unlike constructors, they are not required to create a new object each time they're invoked
        - unlike constructors, they can return an object of any subtype of their return type
        - the class of the returned object can vary from call to call as a function of the input parameters
        - the class of returned object need not exist when the class containing the method is written
     */
    public static Boolean valueOf(boolean b) {
        // instance controlled
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}

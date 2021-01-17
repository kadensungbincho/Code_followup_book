package com.company.item16;

public class Item16 {
    /*
        In public classes, use accessor methods, not public fields

        - If a class is accessible outside its package, provide accessor methods.

        - If a class is package-private or is a private nested class, there is nothing
            inherently wrong with exposing its data fields.
     */
    class Point {
        public double x;
        public double y;
    }
}

package com.company.item10;

import java.util.Objects;
import java.util.stream.Collectors;

public class Transitivity {
    // Transitivity
    public class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }

            Point p = (Point) o;
            return p.x == x && p.y == y;
        }
    }

    public class Color {}

    public class ColorPoint extends Point {
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            // get different results when comparing a point to a color point and vice versa.
            // Point p = new Point(1, 2);
            // ColorPoint cp = new ColorPoint(1, 2, Color.RED);
            // p.equals(cp) returns true, while cp.equals(p) returns false
            if (!(o instanceof ColorPoint))
                return false;

            return super.equals(o) && ((ColorPoint) o).color == color;
        }

        // solution?
        // there is no way to extends an instantiable class and add a value component while preserving the equals contract
    }

    public class ColorPoint1 {
        private final Point point;
        private final Color color;

        public ColorPoint1(int x, int y, Color color) {
            point = new Point(x, y);
            this.color = Objects.requireNonNull(color);
        }

        public Point asPoint() {
            return point;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ColorPoint1))
                return false;

            ColorPoint1 cp = (ColorPoint1) o;
            return cp.point.equals(point) && cp.color.equals(color);
        }
    }
}

package com.company.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    /*
        Simply put, it manages its own memory. The storage pool consists of the elements
        array. The elements in the active portion of the array are allocated, and those in the
        remainder of the array are free. The garbage collector has no way of knowing this; to the
        garbage collector, all of the object references in the elements array are equally valid.
        Only the programmer knows that the inactive portion of the array is unimportant. The programmer
        effectively communicates this fact to the garbage collector by manually nulling out array elements
        as soon as they become part of the inactive portion.

        Generally speaking, whenever a class manages its own memory, the programmer should be alert for
        memory leaks.

        Another common source of memory leaks is caches.

        A third common source of memory leaks is listeners and other callbacks.
     */
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

//    public Object pop() {
//        if (size == 0) {
//            throw new EmptyStackException();
//        }
//        return elements[--size]; // memory leak
//    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        // nulling out object references should be the exception rather than the norm
        elements[size] = null;  // eliminate obsolete reference
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

package com.company.item8;

public class Item8 {
    /*
        - Finalizers are unpredictable, often dangerous, and generally unnecessary.
        - Cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary.

        - Never do anything time-critical in a finalizer or cleaner.

        - You should never depend on a finalizer or cleaner to update persistent state.

        - There is a severe performance penalty for using finalizers and cleaners.

        - Throwing an exception from a constructor should be sufficient to prevent an object from
            coming into existence; in the presence of finalizers, it is not.
        - To protect nonfinal classes from finalizer attacks, write a final finalize method that does nothing.

        - Have your class implement AutoCloseable
     */
    public static void main(String[] args) throws Exception {
        // well-behaved
        try (Room myRoom = new Room(7)) {
            System.out.println("Goodbye");
        }

        // misbehaved
        new Room(99);
        System.out.println("Peace out");
    }
}

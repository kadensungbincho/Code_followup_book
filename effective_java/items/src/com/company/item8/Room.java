package com.company.item8;


import java.lang.ref.Cleaner;

public class Room  implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // It is critical that a State instance does not refer to its Room instance. If it did,
    //   it would create a circularity that would prevent the Room instance from becoming eligible for
    //   garbage collection.
    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    private final State state;

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}

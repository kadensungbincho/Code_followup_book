package com.company.item13;


public class Item13 {
    /*
        Override clone judiciously

        - In practice, a class implementing Cloneable is expected to provide a
            properly functioning public clone method.


        - Java supports covariant return types. In other words, an overriding method's return
            type can be a subclass of the overridden method's return type.

        - If an object contains fields that refer to mutable objects, the simple clone implementation
            shown earlier can be disastrous.

        - In effect, the clone method functions as a constructor; you must ensure that it is does no
            harm to the original object and that it properly establishes invariants on the clone.

        - the Cloneable architecture is incompatible with normal use of final fields referring to mutable objects.

        - A better approach to object copying is to provide a copy constructor or copy factory.
     */

    public class HashTable1 implements Cloneable {
        private Entry[] buckets;

        @Override
        protected Object clone() throws AssertionError {
            try {
                /*
                    Though the clone has its own bucket array, this array references the same
                    linked lists as the original, which can easily cause nondeterministic behavior in
                    both the clone and the original.
                 */
                HashTable1 result = (HashTable1) super.clone();
                result.buckets = buckets.clone();
                return result;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        private class Entry {
            final Object key;
            Object value;
            Entry next;

            Entry(Object key, Object value, Entry next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }



    public class HashTable2 implements Cloneable {
        private Entry[] buckets;

        @Override
        protected Object clone() throws AssertionError {
            try {
                HashTable2 result = (HashTable2) super.clone();
                result.buckets = new Entry[buckets.length];
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != null) {
                        result.buckets[i] = buckets[i].deepCopy();
                    }
                }
                return result;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        private class Entry {
            final Object key;
            Object value;
            Entry next;

            Entry(Object key, Object value, Entry next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public Entry deepCopy() {
                return new Entry(key, value, next == null ? null : next.deepCopy());
            }
        }
    }
}

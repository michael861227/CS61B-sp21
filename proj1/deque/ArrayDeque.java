package deque;

/**
 * Implement a circular ArrayDeque from scratch.
 *  @author Michael Chou
 */

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int size, nextFirst, nextLast;
    private T[] items;

    /**
     * Constructor for ArrayDeque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }

    /**
     * Add an item to the front of the deque
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
    }
    /**
     * Add an item to the back of the deque
     * @param item
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        if (nextLast >= items.length) {
            nextLast = 0;
        }
    }

    /**
     * Private helper function for resizing the deque if capacity is not enough
     * or too much (i.e. usage factor > 25% if size >= 16)
     * @param capacity
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        // ArrayDeque is not fulled
        if (items[nextLast] == null) {
            int startPos = nextFirst + 1;
            if (startPos >= items.length) {
                startPos = 0;
            }
            System.arraycopy(items, startPos, temp, 0, size);
            items = temp;
            nextLast = size;
            nextFirst = items.length - 1;
        }
        // ArrayDeque is fulled
        else {
            int lastTotal = size - nextLast;
            System.arraycopy(items, nextLast, temp, 0, lastTotal);
            System.arraycopy(items, 0, temp, lastTotal, nextFirst + 1);
            items = temp;
            nextLast = size;
            nextFirst = items.length - 1;
        }
    }


    /**
     * @return how many items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Print the deque from first to last, separated from white space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, return null
     * @return removeItem or null
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextFirst += 1;
        if (nextFirst >= items.length) {
            nextFirst = 0;
        }

        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        // Check for ArrayDeque's usage factor
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, return null
     * @return removeItem or null
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nextLast -= 1;
        if (nextLast < 0) {
            nextLast = items.length - 1;
        }

        T removeItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        // Check for ArrayDeque's usage factor
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     *
     * If no such item exists, returns null
     * @param index
     * @return item or null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        int arrayIndex = index + nextFirst + 1;
        if (arrayIndex >= items.length) {
            arrayIndex -= items.length;
        }
        return items[arrayIndex];
    }

    /**
     * Make objects in the deque iterable
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int count, pos;
        public DequeIterator() {
            count = 0;
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            while (pos < items.length && items[pos] == null) {
                pos += 1;
            }

            if (pos < items.length) {
                T returnItem = items[pos];
                pos += 1;
                count += 1;
                return returnItem;
            }

            return null;
        }
    }

    /**
     * @param o
     * @return whether the two object are equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> other = (ArrayDeque<T>) o;

        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /*
    // Use another instanceof way to override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ArrayDeque ad) {
            if (this.size() != ad.size()) {
                return false;
            }
            for (T x : this) {
                if (!ad.contain(x)) {
                    return false;
                }
            }
            return true;

        }

        return false;
    }
    */

}

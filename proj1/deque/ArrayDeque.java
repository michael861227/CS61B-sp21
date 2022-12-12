package deque;

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>{
    private int size, nextFirst, nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }

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


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

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
        if (items.length >= 16 && (double)size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

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
        if (items.length >= 16 && (double)size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        return items[index];
    }

    /* return an iterator into this ArrayDeque class */
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int count, pos;
        public DequeIterator() {
            count = 0;
            pos = 0;
        }
        public boolean hasNext() {
            return count < size;
        }

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

    public boolean contain(T item) {
        for (T x : this) {
            if (x.equals(item)) {
                return true;
            }
        }
        return false;
    }


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


    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
    }
}

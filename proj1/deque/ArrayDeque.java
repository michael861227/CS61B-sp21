package deque;

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class ArrayDeque<T> {
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
        System.arraycopy(items, 0, temp, 0, nextFirst + 1);

        int lastTotal = size - nextLast;
        System.arraycopy(items, nextLast, temp, capacity - lastTotal, lastTotal);
        items = temp;

        nextLast = nextFirst + 1;
        nextFirst = capacity - lastTotal - 1;
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
        if (items.length > 16 && (double)size / items.length < 0.25) {
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
    /*
    public Iterator<T> iterator() {

    }

    public boolean equals(Object o) {

    }

    */

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
    }
}
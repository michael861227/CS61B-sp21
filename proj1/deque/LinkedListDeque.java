package deque;

import java.util.Iterator;
/**
 * Implement a circular LinkedListDeque from scratch.
 *  @author Michael Chou
 */

public class LinkedListDeque<T> implements Iterable<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        /**
         * Create for new Node
         * @param i
         * @param n
         * @param p
         */
        public Node (T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }

        /**
         *  Create for sentinel Node
         */
        public Node(){
            this.next = this;
            this.prev = this;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();

    }

    /**
     * Add an item to the front of the deque
     * @param item
     */
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.prev.prev = sentinel.next;
    }

    /**
     * Add an item to the back of the deque
     * @param item
     */
    public void addLast(T item) {
        size += 1;
        sentinel.prev.next = new Node(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
    }

    /**
     * @return whether the deque is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return how many items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Print the deque from first to last, separated from white space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node f = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(f.item + " ");
            f = f.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, return null
     * @return removeItem or null
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size -= 1;
            T removeItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;

            return removeItem;
        }
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, return null
     * @return removeItem or null
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size -= 1;
            T removeItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;

            return removeItem;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     *
     * If no such item exists, returns null
     * @param index
     * @return item or null
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node f = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                return f.item;
            }
            f = f.next;
        }

        return null;
    }

    /**
     * Private Helper function for the get method in recursive way
     * @param curr
     * @param index
     * @return targetNode
     */
    private Node getCurrentNode(Node curr, int index) {
        if (index == 0) {
            return curr;
        }
        return getCurrentNode(curr.next, index - 1);
    }

    /**
     * Use recursive way same as get method
     * @param index
     * @return item or null
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node target = getCurrentNode(sentinel.next, index);

        return target.item;
    }

    /**
     * Make objects in the deque iterable
     * @return iterator
     */
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int pos;

        public DequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }
        @Override
        public T next() {
            T currItem = get(pos);
            pos += 1;
            return currItem;
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
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }

        if (!ListEqual(other)) {
            return false;
        }

        return true;
    }

    /**
     * Private helper method for equals
     * @param o
     * @return whether two linkedListDeque are equal
     */
    private boolean ListEqual(LinkedListDeque o) {
        Node h1 = this.sentinel;
        Node h2 = o.sentinel;

        while (h1.next.item != null) {
            if (!h1.next.item.equals(h2.next.item)) {
                return false;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    /*
    //Use another instanceof way to override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque lld2) {
            if (this.size() != lld2.size()) {
                return false;
            }
            if (!ListEqual(lld2)) {
                return false;
            }

            return true;
        }
        return false;
    }
    */
}

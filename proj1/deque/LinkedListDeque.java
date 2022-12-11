package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node (T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }

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

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.prev.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.prev.next = new Node(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node f = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(f.item + " ");
            f = f.next;
        }
        System.out.println();
    }

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

    private Node getCurrentNode(Node curr, int index) {
        if (index == 0) {
            return curr;
        }
        return getCurrentNode(curr.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node target = getCurrentNode(sentinel.next, index);

        return target.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos;

        public LinkedListDequeIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            T currItem = get(pos);
            pos += 1;
            return currItem;
        }
    }

//    public boolean equals(Object o) {
//
//    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(10);
        a.addFirst(20);
        a.addLast(30);

        for(int x: a) {
            System.out.println(x);
        }
    }
}

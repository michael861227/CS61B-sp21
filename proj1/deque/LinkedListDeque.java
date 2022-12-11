package deque;

public class LinkedListDeque<T> {
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
        size -= 1;
        T removeItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        return removeItem;
    }

    public T removeLast() {
        size -= 1;
        T removeItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        return removeItem;
    }

//    public T get(int index) {
//
//    }
//
//    public T getRecursive(int index) {
//
//    }
//
//    public Iterator<T> iterator() {
//
//    }
//
//    public boolean equals(Object o) {
//
//    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(10);
        a.addFirst(20);
        a.addLast(30);
        a.printDeque();
    }
}

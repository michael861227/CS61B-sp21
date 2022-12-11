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

    }

    public void addLast(T item) {

    }

    public boolean isEmpty() {

    }

    public int size() {

    }

    public void printDeque() {

    }

    public T removeFirst() {

    }

    public T removeLast() {

    }

    public T get(int index) {

    }

    public T getRecursive(int index) {

    }

    public Iterator<T> iterator() {

    }

    public boolean equals(Object o) {

    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
    }
}

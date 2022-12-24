package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Michael Chou
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /**
     *  Instance Variables
     */
    private Collection<Node>[] buckets;
    private int size;
    private int capacity;
    private int index;
    private double loadFactor;

    /**
     * Constructors
     */
    public MyHashMap() {
        initializeMyHashMap(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        initializeMyHashMap(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        initializeMyHashMap(initialSize, maxLoad);
    }

    private void initializeMyHashMap(int initialSize, double maxLoad) {
        capacity = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(capacity);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }

        return table;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        index = Math.floorMod(key.hashCode(), capacity);
        for (Node item: buckets[index]) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        index = Math.floorMod(key.hashCode(), capacity);
        for (Node item: buckets[index]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot put a null key to HashMap");
        }

        index = Math.floorMod(key.hashCode(), capacity);

        if (containsKey(key)) {
            for (Node item: buckets[index]) {
                if (item.key.equals(key)) {
                    item.value = value;
                    break;
                }
            }
        } else {
            buckets[index].add(createNode(key, value));
            size += 1;
        }

        if ((double) size / capacity >= loadFactor) {
            resize(capacity * 2);
        }
    }

    private void resize(int newCapacity) {
        List<Node> list = getAllNode();
        initializeMyHashMap(newCapacity, this.loadFactor);
        for (Node item: list) {
            index = Math.floorMod(item.key.hashCode(), capacity);
            buckets[index].add(createNode(item.key, item.value));
        }
    }

    private LinkedList<Node> getAllNode() {
        LinkedList<Node> list = new LinkedList<>();
        for (Collection<Node> bucket: buckets) {
            list.addAll(bucket);
        }

        return list;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (K key: this) {
            set.add(key);
        }

        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new MyMapIterator();
    }

    private class MyMapIterator implements Iterator<K> {
        private List<Node> list;
        public MyMapIterator() {
            list = new LinkedList<>();
            for (int i = 0; i < capacity; i++) {
                list.addAll(buckets[i]);
            }
        }
        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public K next() {
            return list.remove(0).key;
        }
    }


}

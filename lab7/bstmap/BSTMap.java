package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        private BSTNode left, right;
        private final K key;
        private V val;

        private int size;

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private BSTNode root;

    public BSTMap() {
        root = null;
    }

    /**
     * Removes all of the mappings from this map.
     * */
    @Override
    public void clear() {
        root = null;
    }

    /**
     *  Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }

        BSTNode curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return false;
    }

    /**
     *  Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode root, K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot call get() with a null key");
        }

        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root.val;
        } else if (cmp < 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    /**
     *  Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + size(root.right) + 1;
    }

    /**
     *  Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot put a null key to BSTMap");
        }

        root = put(root, key, value);
    }

    private BSTNode put(BSTNode root, K key, V value) {
        if (root == null) {
            return new BSTNode(key, value, 1);
        }

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.val = value;
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }

        if (cmp != 0) {
            root.size = size(root.left) + size(root.right) + 1;
        }

        return root;
    }

    /**
     *  Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     *  Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Prints out BSTMap in order of increasing Key.
     */
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode root) {
        if (root == null) {
            System.out.println("The BSTMap is empty.");
        }

        printInOrder(root.left);
        System.out.println(root.key.toString() + "->" + root.val.toString());
        printInOrder(root.right);
    }
}

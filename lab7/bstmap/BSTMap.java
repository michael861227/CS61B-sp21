package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        private BSTNode left, right;
        private K key;
        private V val;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        size = 0;
        root = null;
    }

    /**
     * Removes all of the mappings from this map.
     * */
    @Override
    public void clear() {
        size = 0;
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
        return size;
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
            size += 1;
            return new BSTNode(key, value);
        }

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.val = value;
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }

        return root;
    }

    /**
     *  Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
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
     *  Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot called remove() with a null key.");
        }

        if (!containsKey(key)) {
            return null;
        }

        V removeVal = get(key);
        root = remove(root, key);

        return removeVal;
    }

    /**
     * Hibbard deletion
     * Replace the remove node with its successor.
     */
    private BSTNode remove(BSTNode root, K key) {
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = remove(root.left, key);
        } else if (cmp > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.right == null) {
                size -= 1;
                return root.left;
            }
            if (root.left == null) {
                size -= 1;
                return root.right;
            }

            size -= 1;
            BSTNode succ = findSuccessor(root);
            root.key = succ.key;
            root.val = succ.val;

        }

        return root;
    }

    private BSTNode findSuccessor(BSTNode root) {
        BSTNode curr = root.right;

        while (curr.left != null) {
            root = curr;
            curr = curr.left;
        }

        if (curr == root.right) {
            root.right = curr.right;
        } else {
            if (curr.right != null) {
                root.left = curr.right;
            } else {
                root.left = null;
            }
        }

        return curr;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot called remove() with a null key.");
        }

        if (!containsKey(key)) {
            return null;
        }

        V removeVal = get(key);

        // Check for the specific value
        if (removeVal.equals(value)) {
            remove(key);
        }

        return null;
    }

    /**
     * Return an iterator over the keys
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private List<BSTNode> list;

        public BSTMapIterator() {
            list = new LinkedList<>();
            list.add(root);
        }
        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public K next() {
            BSTNode node = list.remove(0);

            if (node.left != null) {
                list.add(node.left);
            }

            if (node.right != null) {
                list.add(node.right);
            }

            return node.key;
        }
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

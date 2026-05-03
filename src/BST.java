import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.BSTEntry> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public class BSTEntry {
        private K key;
        private V value;

        public BSTEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public BST() {
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new Node(key, val);
        } else {
            parent.right = new Node(key, val);
        }
        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) break; // Узел найден

            parent = current;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        if (current.left == null || current.right == null) {
            Node newChild = (current.left == null) ? current.right : current.left;

            if (parent == null) {
                root = newChild;
            } else if (parent.left == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
            size--;
            return;
        }

        Node successorParent = current;
        Node successor = current.right;

        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }

        current.key = successor.key;
        current.val = successor.val;

        if (successorParent.left == successor) {
            successorParent.left = successor.right;
        } else {
            successorParent.right = successor.right;
        }
        size--;
    }

    @Override
    public Iterator<BSTEntry> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<BSTEntry> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public BSTEntry next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return new BSTEntry(node.key, node.val);
        }
    }
}
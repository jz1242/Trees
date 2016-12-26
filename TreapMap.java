/** Jason Zhang jzhan127
 * 
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TreapMap <K extends Comparable<? super K>, V> implements OrderedMap<K, V> {
    private class Node {
        Node left;
        Node right;
        K key;
        V value;
        int priority;
        
        // Constructor to make node creation easier to read.
        Node(K k, V v) {
            // left and right default to null
            Random rand = new Random();
            this.key = k;
            this.value = v;
            this.priority = rand.nextInt();
        }
        
        // Just for debugging purposes.
        public String toString() {
            return "Node<key: " + this.key
                + "; value: " + this.value
                + ">";
        }
    }
    private Node root;
    private int size;
    
    private Node find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        Node n = this.root;
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }
    
    private Node findForSure(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        return n;
    }
    
    private int priority(Node k){
        if(k == null){
            return -1;
        }
        return k.priority;
    }

    @Override
    public void insert(K k, V v) throws IllegalArgumentException {
        Node a = this.find(k);
        if(k == null){
            throw new IllegalArgumentException();
        }
        this.root = this.insert(this.root, k, v);
        size++;
        
        
    }
    private Node insert(Node n, K k, V v) {
        if (n == null) {
            Node s = new Node(k, v);
            return s;
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.insert(n.left, k, v);
            if(this.priority(n) > this.priority(n.left)){

                    n = rotateRight(n);                    
            }
            
        } else if (cmp > 0) {
            n.right = this.insert(n.right, k, v);
            if(this.priority(n) > this.priority(n.right)){
                    n = rotateLeft(n);
                }

            }
            else {
            throw new IllegalArgumentException("duplicate key " + k);
        }

        return n;
    }
    
    private Node rotateRight(Node n){
        Node s = n.left;
        n.left = s.right;
        s.right = n;
        return s;
    }
    private Node rotateLeft(Node n){
        Node s = n.right;
        n.right = s.left;
        s.left = n;
        return s;

    }

    @Override
    public V remove(K k) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        V v = this.findForSure(k).value;
        this.root = this.remove(this.root, k);
        this.size -= 1;
        return v;
    }
    private Node remove(Node n, K k){
        if (n == null) {
            throw new IllegalArgumentException();
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.remove(n.left, k);
            if(this.priority(n) > this.priority(n.left)){
                    n = rotateRight(n);                    
            }
            
        } else if (cmp > 0) {
            n.right = this.remove(n.right, k);
            if(this.priority(n) > this.priority(n.right)){
                    n = rotateLeft(n);
            }
        } else {
            n = this.remove(n);
        }
        return n;
    }
    // Remove given node and return the remaining tree.
    // Easy if the node has 0 or 1 child; if it has two
    // children, find the predecessor, copy its data to
    // the given node (thus removing the key we need to
    // get rid off), the remove the predecessor node.
    private Node remove(Node n) {
        // 0 and 1 child
        if (n.left == null) {
            return n.right;
        }
        if (n.right == null) {
            return n.left;
        }

        // 2 children
        Node max = this.max(n.left);
        n.key = max.key;
        n.value = max.value;
        n.left = this.remove(n.left, max.key);
        return n;
    }

    // Return node with maximum key in subtree rooted
    // at given node. (Iterative version because once
    // again recursion has no advantage here.)
    private Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    @Override
    public void put(K k, V v) throws IllegalArgumentException {
        Node n = this.findForSure(k);
        n.value = v;
        
    }

    @Override
    public V get(K k) throws IllegalArgumentException {
        Node n = this.findForSure(k);
        return n.value;
    }

    @Override
    public boolean has(K k) {
        // TODO Auto-generated method stub
        if(k == null){
            return false;
        }
        return this.find(k) != null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.size;
    }
    
    // Recursively add keys from subtree rooted at given node
    // into the given list.
    private void iteratorHelper(Node n, List<K> keys) {
        if (n == null) {
            return;
        }
        this.iteratorHelper(n.left, keys);
        keys.add(n.key);
        this.iteratorHelper(n.right, keys);
    }
    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        List<K> keys = new ArrayList<K>();
        this.iteratorHelper(this.root, keys);
        return keys.iterator();
    }
    
}



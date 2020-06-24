public class HashMapImpl<K, V> {
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    private int length;
    private Node<K, V>[] nodes;

    public HashMapImpl() {
        length = 7;
        nodes = new Node[length];
    }

    public void put(K key, V value) {
        if ((double) (size / length) > LOAD_FACTOR) {
            resize();
        }
        putNode(key, value);
    }

    public V get(K key) {
        int prob = 0;
        do {
            int hash = getHash(key, prob);
            Node<K, V> node = nodes[hash];
            if (node != null && node.key.equals(key)) {
                return node.value;
            }
            prob++;
        } while (prob <= length);
        throw new RuntimeException("Can't get a value");
    }

    public int size() {
        return size;
    }

    private void putNode(K key, V value) {
        int prob = 0;
        do {
            int hash = getHash(key, prob);
            if (nodes[hash] == null) {
                nodes[hash] = new Node<>(key, value);
                size++;
                return;
            } else if (nodes[hash].key.equals(key)) {
                nodes[hash].value = value;
            }
            prob++;
        } while (prob <= length);
        throw new RuntimeException("Can't put a value");
    }

    private void resize() {
        length = getNextPrime();
        Node<K, V>[] oldNodes = nodes;
        size = 0;
        nodes = new Node[length];
        for (Node<K, V> oldNode : oldNodes) {
            putNode(oldNode.key, oldNode.value);
        }
    }

    private int getNextPrime() {
        int prime = length * 2;
        while (true) {
            int count = 0;
            for (int i = 2; i <= prime / 2 && count == 0; i++) {
                if (prime % i == 0) {
                    count++;
                }
            }
            if (count == 0) {
                return prime;
            }
            prime++;
        }
    }

    private int getHash(K key, int prob) {
        int hash1 = key.hashCode() % length;
        int hash2 = (5 - (key.hashCode() % 5));
        return Math.abs(hash1 + (prob * hash2)) % length;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

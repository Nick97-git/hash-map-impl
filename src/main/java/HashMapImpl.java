public class HashMapImpl {
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    private int length;
    private Node[] nodes;

    public HashMapImpl() {
        length = 7;
        nodes = new Node[length];
    }

    public void put(int key, long value) {
        if ((double) (size / length) > LOAD_FACTOR) {
            resize();
        }
        putNode(key, value);
    }

    public long get(int key) {
        int prob = 0;
        do {
            int hash = getHash(key, prob);
            Node node = nodes[hash];
            if (node != null && node.key == key) {
                return node.value;
            }
            prob++;
        } while (prob <= length);
        throw new RuntimeException("Can't get a value");
    }

    public int size() {
        return size;
    }

    private void putNode(int key, long value) {
        int prob = 0;
        do {
            int hash = getHash(key, prob);
            if (nodes[hash] == null) {
                nodes[hash] = new Node(key, value);
                size++;
                return;
            } else if (nodes[hash].key == key) {
                nodes[hash].value = value;
            }
            prob++;
        } while (prob <= length);
        throw new RuntimeException("Can't put a value");
    }

    private void resize() {
        length = getNextPrime();
        Node[] oldNodes = nodes;
        size = 0;
        nodes = new Node[length];
        for (Node oldNode : oldNodes) {
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

    private int getHash(int key, int prob) {
        int hash1 = key % length;
        int hash2 = (5 - (key % 5));
        return Math.abs(hash1 + (prob * hash2)) % length;
    }

    private static class Node {
        private final int key;
        private long value;

        public Node(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }
}

import java.util.Comparator;

/** Abstract base class for implementations of the PriorityQueue interface. */
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        protected void setKey(K key) {
            this.key = key;
        }

        protected void setValue(V value) {
            this.value = value;
        }
    }

    private Comparator<K> comparator;

    protected AbstractPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    protected int compare(Entry<K, V> first, Entry<K, V> second) {
        return comparator.compare(first.getKey(), second.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return comparator.compare(key, key) == 0;
        } catch (ClassCastException exception) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}

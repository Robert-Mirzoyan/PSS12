import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A map implementation using an unsorted array list. */
public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public UnsortedTableMap() {
    }

    private int findIndex(K key) {
        int size = table.size();
        for (int index = 0; index < size; index++) {
            if (table.get(index).getKey().equals(key)) {
                return index;
            }
        }
        return -1;
    }

    public int size() {
        return table.size();
    }

    public V get(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return null;
        }
        return table.get(index).getValue();
    }

    public V put(K key, V value) {
        int index = findIndex(key);
        if (index == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        }
        return table.get(index).setValue(value);
    }

    public V remove(K key) {
        int index = findIndex(key);
        int size = size();
        if (index == -1) {
            return null;
        }
        V answer = table.get(index).getValue();
        if (index != size - 1) {
            table.set(index, table.get(size - 1));
        }
        table.remove(size - 1);
        return answer;
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int index = 0;

        public boolean hasNext() {
            return index < table.size();
        }

        public Entry<K, V> next() {
            if (index == table.size()) {
                throw new NoSuchElementException();
            }
            return table.get(index++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }
}

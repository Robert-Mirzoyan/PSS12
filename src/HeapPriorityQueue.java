import java.util.ArrayList;
import java.util.Comparator;

/** An implementation of a priority queue using an array-based heap. */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    protected int parent(int index) {
        return (index - 1) / 2;
    }

    protected int left(int index) {
        return 2 * index + 1;
    }

    protected int right(int index) {
        return 2 * index + 2;
    }

    protected boolean hasLeft(int index) {
        return left(index) < heap.size();
    }

    protected boolean hasRight(int index) {
        return right(index) < heap.size();
    }

    protected void swap(int first, int second) {
        Entry<K, V> temporary = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temporary);
    }

    protected void upheap(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    protected void downheap(int index) {
        while (hasLeft(index)) {
            int smallChildIndex = left(index);
            if (hasRight(index)) {
                int rightIndex = right(index);
                if (compare(heap.get(smallChildIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(heap.get(smallChildIndex), heap.get(index)) >= 0) {
                break;
            }
            swap(index, smallChildIndex);
            index = smallChildIndex;
        }
    }

    public int size() {
        return heap.size();
    }

    public Entry<K, V> min() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
        return newest;
    }

    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            downheap(0);
        }
        return answer;
    }
}

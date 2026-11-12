import java.util.Comparator;

/** Comparator that uses the natural ordering of its elements. */
public class DefaultComparator<E> implements Comparator<E> {
    @SuppressWarnings("unchecked")
    public int compare(E first, E second) throws ClassCastException {
        return ((Comparable<E>) first).compareTo(second);
    }
}

import java.util.Arrays;

/** Runs tests only for the coding problems in PSS12. */
public class Main {
    public static void main(String[] args) {
        testProblem2();
        testProblem3();
        testProblem4();
        testProblem5();
        testOptionalProblem1();
        testOptionalProblem2();

        System.out.println("All coding-problem tests passed.");
    }

    private static void testProblem2() {
        RecursiveHeapPriorityQueue<Integer, Integer> queue =
                new RecursiveHeapPriorityQueue<>();

        int[] values = {2, 5, 6, 9, 7, 8, 1};
        for (int value : values) {
            queue.insert(value, value);
        }

        int[] expectedOrder = {1, 2, 5, 6, 7, 8, 9};
        for (int expected : expectedOrder) {
            Entry<Integer, Integer> removed = queue.removeMin();
            check(removed != null && removed.getKey() == expected,
                    "Problem 2 returned an incorrect minimum.");
        }
        check(queue.isEmpty(), "Problem 2 queue should be empty.");
        System.out.println("Problem 2 tests passed.");
    }

    private static void testProblem3() {
        int[] data = {6, 5, 3, 2, 8, 10, 9};
        Problem3Solution.sortNearlySorted(data, 3);
        check(Arrays.equals(data, new int[]{2, 3, 5, 6, 8, 9, 10}),
                "Problem 3 did not sort the example correctly.");

        int[] alreadySorted = {1, 2, 3, 4};
        Problem3Solution.sortNearlySorted(alreadySorted, 0);
        check(Arrays.equals(alreadySorted, new int[]{1, 2, 3, 4}),
                "Problem 3 failed for k = 0.");

        int[] shortArray = {3, 1, 2};
        Problem3Solution.sortNearlySorted(shortArray, 10);
        check(Arrays.equals(shortArray, new int[]{1, 2, 3}),
                "Problem 3 failed when k is larger than the array.");
        System.out.println("Problem 3 tests passed.");
    }

    private static void testProblem4() {
        check(Problem4Solution.areAnagrams("listen", "silent"),
                "Problem 4: listen and silent should be anagrams.");
        check(Problem4Solution.areAnagrams("triangle", "integral"),
                "Problem 4: triangle and integral should be anagrams.");
        check(!Problem4Solution.areAnagrams("apple", "paper"),
                "Problem 4: apple and paper should not be anagrams.");
        check(!Problem4Solution.areAnagrams("A", "a"),
                "Problem 4 should be case-sensitive.");
        check(Problem4Solution.areAnagrams("a! ", " !a"),
                "Problem 4 should count spaces and punctuation.");
        System.out.println("Problem 4 tests passed.");
    }

    private static void testProblem5() {
        Map<Integer, Integer> first = new UnsortedTableMap<>();
        first.put(2, 5);
        first.put(1, 4);
        first.put(0, 2);

        Map<Integer, Integer> second = new UnsortedTableMap<>();
        second.put(1, 5);
        second.put(0, 5);

        Map<Integer, Integer> result =
                Problem5Solution.addPolynomials(first, second);

        check(valueIs(result, 2, 5), "Problem 5: coefficient of x^2 is wrong.");
        check(valueIs(result, 1, 9), "Problem 5: coefficient of x is wrong.");
        check(valueIs(result, 0, 7), "Problem 5: constant coefficient is wrong.");
        check(result.size() == 3, "Problem 5 result has an unexpected term.");

        check(valueIs(first, 1, 4) && valueIs(second, 1, 5),
                "Problem 5 modified an input map.");

        Map<Integer, Integer> negative = new UnsortedTableMap<>();
        negative.put(2, -5);
        Map<Integer, Integer> cancelled =
                Problem5Solution.addPolynomials(first, negative);
        check(cancelled.get(2) == null,
                "Problem 5 should omit a term whose coefficient becomes zero.");
        System.out.println("Problem 5 tests passed.");
    }

    private static void testOptionalProblem1() {
        int[] heap = {1, 4, 2, 7, 6, 3, 5};
        int[] originalValues = heap.clone();

        OptionalProblem1Solution.convertToMaxHeap(heap);
        check(isMaxHeap(heap),
                "Optional Problem 1 did not produce a max-heap.");

        Arrays.sort(heap);
        Arrays.sort(originalValues);
        check(Arrays.equals(heap, originalValues),
                "Optional Problem 1 changed the set of values.");
        System.out.println("Optional Problem 1 tests passed.");
    }

    private static void testOptionalProblem2() {
        Map<Integer, String> map = new UnsortedTableMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        OptionalProblem2Solution.rename(map, 1, 4);
        check(map.get(1) == null && "A".equals(map.get(4)),
                "Optional Problem 2 failed to replace key 1 with key 4.");

        OptionalProblem2Solution.rename(map, 2, 3);
        check("C".equals(map.get(2)) && "B".equals(map.get(3)),
                "Optional Problem 2 failed to exchange the values.");

        int oldSize = map.size();
        OptionalProblem2Solution.rename(map, 100, 200);
        OptionalProblem2Solution.rename(map, 4, 4);
        check(map.size() == oldSize && "A".equals(map.get(4)),
                "Optional Problem 2 changed the map in a no-change case.");
        System.out.println("Optional Problem 2 tests passed.");
    }

    private static boolean valueIs(
            Map<Integer, Integer> map,
            int key,
            int expectedValue) {
        Integer actualValue = map.get(key);
        return actualValue != null && actualValue == expectedValue;
    }

    private static boolean isMaxHeap(int[] heap) {
        for (int parent = 0; parent < heap.length / 2; parent++) {
            int left = 2 * parent + 1;
            int right = left + 1;
            if (heap[parent] < heap[left]) {
                return false;
            }
            if (right < heap.length && heap[parent] < heap[right]) {
                return false;
            }
        }
        return true;
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}

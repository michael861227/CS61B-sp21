package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxIntCompareTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(getIntComparator());
        int maxValue = 9;
        for (int i = 0; i < 10; i++) {
            mad.addFirst(i);
        }

        assertEquals("The max item should be same with maxValue", maxValue, mad.max(), 0.0);
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    @Test
    public void maxStringCompareTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(getStringComparator());
        mad.addFirst("Michael");
        mad.addFirst("Johnson");
        mad.addLast("Sandy");
        mad.addFirst("Genie");

        assertEquals("Sandy", mad.max());
        assertEquals("Johnson", mad.max(getLongestStringComparator()));

    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static Comparator<String> getLongestStringComparator() {
        return new longestStringComparator();
    }

    private static class longestStringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    @Test
    public void randomizedTest(){
        MaxArrayDeque<Integer> correct = new MaxArrayDeque<>(getIntComparator());
        MaxArrayDeque<Integer> broken = new MaxArrayDeque<>(getIntComparator());

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);

                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 1) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 2) {
                // getLast
                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 3) {
                // removeLast
                assertEquals(correct.removeLast(), broken.removeLast());
            }
        }
    }
}

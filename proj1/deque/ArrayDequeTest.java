package deque;

import static org.junit.Assert.*;

import net.sf.saxon.ma.arrays.ArrayFunctionSet;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Iterator;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertTrue("ArrayDeque should be empty", ad1.isEmpty());

        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        assertEquals("The size should be 3", 3, ad1.size());

        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");

        ad1.addFirst("Z");
        assertEquals("The size should be 9", 9, ad1.size());

        ad1.printDeque();

    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        /* Test for removeFirst */
        ad1.addFirst("a");
        ad1.addFirst("b");
        ad1.addFirst("c");
        ad1.addFirst("d");

        ad1.removeFirst();
        assertEquals("The size of ArrayDeque should be 3", 3, ad1.size());
        ad1.printDeque();

        /* Test for removeLast */
        ad2.addLast("a");
        ad2.addLast("b");
        ad2.addLast("c");
        ad2.addLast("d");
        ad2.removeLast();
        assertEquals("The size of ArrayDeque should be 3", 3, ad2.size());
        ad2.printDeque();
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertTrue("ArrayDeque should be empty", ad1.isEmpty());
        ad1.addFirst("a");
        ad1.removeFirst();
        assertNull(ad1.removeFirst());

        assertNull(ad1.removeLast());
    }

    @Test
    public void multipleParamTest() {
        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }


    @Test
    public void bigADequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 50; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 99; i > 50; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(10);
        ad1.addLast(5);
        ad1.addLast(1);
        ad1.addLast(0);
        ad1.addFirst(30);

        assertEquals("Should have the same value", 30, (double)ad1.get(3), 0.0);
        assertEquals("Should have the same value", 10, (double)ad1.get(4), 0.0);
        assertNull("The value should be null", ad1.get(1));
        assertNull("The value should be null", ad1.get(2));

    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);

        for (Integer x : ad1) {
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(4);
        ad2.addLast(5);
        ad2.addLast(6);
        ad2.addLast(7);
        ad2.addLast(8);

        for (Integer x : ad2) {
            System.out.print(x);
            System.out.print(" ");
        }
    }
    @Test
    public void equalTest() {

    }
}

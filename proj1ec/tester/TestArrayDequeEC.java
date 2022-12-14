package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder("");

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);

            // addFirst
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(100);
                student.addFirst(randVal);
                correct.addFirst(randVal);

                msg.append("addFirst(");
                msg.append(Integer.toString(randVal));
                msg.append(")\n");

                assertEquals(msg.toString(), correct.get(0), student.get(0));
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(100);
                student.addLast(randVal);
                correct.addLast(randVal);

                msg.append("addLast(");
                msg.append(Integer.toString(randVal));
                msg.append(")\n");

                assertEquals(msg.toString(), correct.get(correct.size() - 1), student.get(student.size() - 1));
            } else if (operationNumber == 2 && correct.size() != 0 && student.size() != 0) {
                // removeFirst
                Integer expect = correct.removeFirst();
                Integer actual = student.removeFirst();

                msg.append("removeFirst()\n");

                assertEquals(msg.toString(), expect, actual);
            } else if (operationNumber == 3 && correct.size() != 0 && student.size() != 0) {
                // removeLast
                Integer expect = correct.removeLast();
                Integer actual = student.removeLast();

                msg.append("removeLast()\n");

                assertEquals(msg.toString(), expect, actual);
            }
        }

    }
}

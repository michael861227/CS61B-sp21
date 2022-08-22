package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int M = 10000;
        int baseSize = 1000;
        int count = 8;
        SLList<Integer> Test;

        // construct SLList
        for (int i = 0; i < count; i++)
        {
            Test = new SLList<>();
            for (int j = 0; j < baseSize; j++)
            {
                Test.addLast(0);
            }

            // start the timer
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < M; k++)
            {
                int temp = Test.getLast();
            }
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(baseSize);
            times.addLast(timeInSeconds);
            opCounts.addLast(M);

            baseSize *= 2;
        }

        printTimingTable(Ns, times, opCounts);
    }

}

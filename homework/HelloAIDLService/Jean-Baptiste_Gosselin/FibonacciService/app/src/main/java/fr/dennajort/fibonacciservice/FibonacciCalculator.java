package fr.dennajort.fibonacciservice;

/**
 * Created by Jean-Baptiste on 13/12/2014.
 */

public class FibonacciCalculator {
    public static long javaRecursive(long n) {
        return n <= 0 ? 0 : n == 1 ? 1 : javaRecursive(n - 1) + javaRecursive(n - 2);
    }

    public static long javaIterative(long n) {
        long previous = -1;
        long result = 1;
        for (long i = 0; i <= n; i++) {
            long sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
    }

    public native static long nativeRecursive(long n);

    public native static long nativeIterative(long n);

    static {
        System.loadLibrary("fr_dennajort_fibonacciservice_FibonacciCalculator"); // <5>
    }
}

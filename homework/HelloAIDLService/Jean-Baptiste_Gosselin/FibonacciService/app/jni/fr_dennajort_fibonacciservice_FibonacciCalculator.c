#include "fr_dennajort_fibonacciservice_FibonacciCalculator.h"
#include <android/log.h>

static jlong fib(jlong n) {
	return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
}
/* Actual implementation of JNI-defined `nativeRecursive` (recursive) function */
JNIEXPORT jlong JNICALL Java_fr_dennajort_fibonacciservice_FibonacciCalculator_nativeRecursive(JNIEnv *env, jclass clazz, jlong n) {
	return fib(n);
}

/* Actual implementation of JNI-defined `nativeIterative` (iterative) function */
JNIEXPORT jlong JNICALL Java_fr_dennajort_fibonacciservice_FibonacciCalculator_nativeIterative(JNIEnv *env, jclass clazz, jlong n) {
	jlong previous = -1;
	jlong result = 1;
	jlong i;
	for (i = 0; i <= n; i++) {
		jlong sum = result + previous;
		previous = result;
		result = sum;
	}
	return result;
}
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := fr_dennajort_fibonacciservice_FibonacciCalculator.c

LOCAL_MODULE := fr_dennajort_fibonacciservice_FibonacciCalculator

LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)
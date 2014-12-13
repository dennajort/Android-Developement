package fr.dennajort.fibonacciservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;

import fr.dennajort.fibonaccicommon.FibonacciRequest;
import fr.dennajort.fibonaccicommon.FibonacciResponse;
import fr.dennajort.fibonaccicommon.IFibonacciService;

public class FibonacciService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    private final IFibonacciService.Stub mBinder = new IFibonacciService.Stub() {

        @Override
        public FibonacciResponse calculate(FibonacciRequest request) throws RemoteException {
            long result;
            long n = request.getN();
            long duration = SystemClock.uptimeMillis();

            switch (request.getType()) {
                case ITERATIVE_JAVA:
                    result = FibonacciCalculator.javaIterative(n);
                    break;
                case RECURSIVE_JAVA:
                    result = FibonacciCalculator.javaRecursive(n);
                    break;
                case ITERATIVE_NATIVE:
                    result = FibonacciCalculator.nativeIterative(n);
                    break;
                case RECURSIVE_NATIVE:
                    result = FibonacciCalculator.nativeRecursive(n);
                    break;
                default:
                    return null;
            }
            duration = SystemClock.uptimeMillis() - duration;
            return new FibonacciResponse(result, duration);
        }
    };
}

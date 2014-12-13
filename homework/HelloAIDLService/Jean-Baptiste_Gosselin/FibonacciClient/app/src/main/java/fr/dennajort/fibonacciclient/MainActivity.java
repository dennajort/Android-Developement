package fr.dennajort.fibonacciclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import fr.dennajort.fibonaccicommon.FibonacciRequest;
import fr.dennajort.fibonaccicommon.FibonacciResponse;
import fr.dennajort.fibonaccicommon.IFibonacciService;

public class MainActivity extends Activity {
    static private String TAG = "FibonacciClient";

    private GRadioGroup gRadioGroup = null;
    private TextView textResult = null;
    private IFibonacciService mFibonacciService = null;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRunning = false;
        setContentView(R.layout.activity_main);
        textResult = (TextView) findViewById(R.id.textResponse);
        gRadioGroup = new GRadioGroup(
                (RadioButton) findViewById(R.id.radioButtonJI),
                (RadioButton) findViewById(R.id.radioButtonJR),
                (RadioButton) findViewById(R.id.radioButtonNI),
                (RadioButton) findViewById(R.id.radioButtonNR));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent i = new Intent();
        i.setClassName("fr.dennajort.fibonacciservice", "fr.dennajort.fibonacciservice.FibonacciService");
        bindService(i, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFibonacciService != null) {
            unbindService(mConnection);
        }
    }

    public void startFibonacci(View view) {
        if (mFibonacciService == null) {
            Log.e(TAG, "No FibonacciService");
            return;
        }

        FibonacciRequest.Type type;

        switch (gRadioGroup.getCurrentId()) {
            case R.id.radioButtonJI:
                type = FibonacciRequest.Type.ITERATIVE_JAVA;
                break;
            case R.id.radioButtonJR:
                type = FibonacciRequest.Type.RECURSIVE_JAVA;
                break;
            case R.id.radioButtonNI:
                type = FibonacciRequest.Type.ITERATIVE_NATIVE;
                break;
            case R.id.radioButtonNR:
                type = FibonacciRequest.Type.RECURSIVE_NATIVE;
                break;
            default:
                Log.e(TAG, "Wrong getCurrentId");
                return;
        }
        EditText argsText = (EditText) findViewById(R.id.argsText);
        FibonacciRequest request = new FibonacciRequest(Long.parseLong(argsText.getText().toString()), type);
        if (isRunning)
            return;
        isRunning = true;
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setEnabled(false);
        new AsyncFibonacciService().execute(request);
    }

    private class AsyncFibonacciService extends AsyncTask<FibonacciRequest, Void, FibonacciResponse> {

        @Override
        protected FibonacciResponse doInBackground(FibonacciRequest... params) {
            FibonacciResponse response;
            FibonacciRequest request = params[0];

            try {
                response = mFibonacciService.calculate(request);
            } catch (RemoteException e) {
                Log.e(TAG, "Got RemoteException");
                return null;
            }
            if (response == null)
                return null;
            return response;
        }

        @Override
        protected void onPostExecute(FibonacciResponse result) {
            if (result == null)
                Log.e(TAG, "Result is null");
            else {
                textResult.setText("Result: " + result.getResult() + "\nDuration: " + result.getDuration() + "ms");
            }
            Button startButton = (Button) findViewById(R.id.startButton);
            startButton.setEnabled(true);
            isRunning = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mFibonacciService = IFibonacciService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mFibonacciService = null;
        }
    };

}

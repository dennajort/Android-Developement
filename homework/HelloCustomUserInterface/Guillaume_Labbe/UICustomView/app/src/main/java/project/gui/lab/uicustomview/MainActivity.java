package project.gui.lab.uicustomview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickItem(View view) {
        switch (view.getId()) {
            case R.id.img1:
                Toast.makeText(getApplicationContext(), " consult ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img2:
                Toast.makeText(getApplicationContext(), " screen ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img3:
                Toast.makeText(getApplicationContext(), " chat ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img4:
                Toast.makeText(getApplicationContext(), " space ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img5:
                Toast.makeText(getApplicationContext(), " web ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img6:
                Toast.makeText(getApplicationContext(), " movies ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img7:
                Toast.makeText(getApplicationContext(), " media ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

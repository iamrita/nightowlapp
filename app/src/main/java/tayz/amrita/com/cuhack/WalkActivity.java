package tayz.amrita.com.cuhack;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WalkActivity extends ActionBarActivity implements SensorEventListener, View.OnClickListener {

    private boolean isWorking;
    private SensorManager sensorManager;
    private TextView numSteps;
    private String currentFinal;
    private float text;
    private TextView current;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        currentFinal = String.valueOf(0);
       // numSteps = (TextView) findViewById(R.id.count);
        //    reset = (Button) findViewById(R.id.button);
        System.out.println("Starting");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //  reset.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isWorking = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isWorking = false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isWorking) {
            if(currentFinal.equals(String.valueOf(0))){
                currentFinal = String.valueOf(event.values[0]);
            }
            text = event.values[0] - Float.parseFloat(currentFinal);
          //  numSteps.setText(String.valueOf(text));

            if(Math.abs(text)>7 ){
                System.out.println("resume place");
                startActivity(new Intent(WalkActivity.this, DetectionType.class));

                AlarmRoutine.song.stop();

            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }


    @Override
    public void onClick(View v) {


    }
}
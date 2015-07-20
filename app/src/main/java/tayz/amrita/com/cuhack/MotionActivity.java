package tayz.amrita.com.cuhack;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MotionActivity extends ActionBarActivity implements SensorEventListener{
    // Start with some variables
    public SensorManager sensorMan;
    public Sensor accelerometer;
    private long lastUpdate = 0;
    public boolean isShaking;
    private long currentTime = 0;
    CountDownTimer counter;
    private boolean inTick;
    public boolean songPlaying;


   public TextView timer;
    public long start = 60000;
    public AlarmRoutine alarm;



    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 110;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        // In onCreate method
        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       sensorMan.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        timer = (TextView)findViewById(R.id.timer);
        resetTimer(10000);
        inTick = false;
        alarm = new AlarmRoutine(this, this, this);





    }

    public void resetTimer(long s) {

         counter =  new CountDownTimer(s, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText(""+(millisUntilFinished / 1000));
                    currentTime = millisUntilFinished / 1000;
                }

                public void onFinish() {
                    timer.setText("...");
                    alarm.tick();
                    inTick = true;
                    counter.cancel();
                }
            }.start();
        }



    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor mySensor = event.sensor;


        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();




            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;


                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    if (inTick)
                    {
                        alarm.stopTick();
                        
                        inTick = false;
                    }
                    else {

                        counter.cancel();
                        resetTimer(10000);
                    }


                } else {

                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_motion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package tayz.amrita.com.cuhack;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class Alarm extends ActionBarActivity implements View.OnClickListener{

    private CountDownTimer timer2;
    private Button tapped;
    private MediaPlayer mp;
    private boolean noAlarm = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        tapped = (Button) findViewById(R.id.button2);
        tapped.setOnClickListener(this);
        mp = MediaPlayer.create(Alarm.this,R.raw.alarm);
        timer2 = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                System.out.println("Timer Finish");
                mp.start();
                noAlarm = false;



            }
        }.start();
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

    @Override
    public void onClick(View v) {

        if (noAlarm) {
            timer2.cancel();
            startActivity(new Intent(Alarm.this, DimTimeScreen.class));

        } else {
            mp.stop();
            startActivity(new Intent(Alarm.this, PlaceHolder.class));
        }
    }
}

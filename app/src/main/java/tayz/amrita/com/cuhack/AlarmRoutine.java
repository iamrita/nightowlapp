package tayz.amrita.com.cuhack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

/**
 * Created by Shangyu on 6/20/2015.
 */
public class AlarmRoutine{
    public static MediaPlayer song;
    public MediaPlayer tick;
    public TextView songName, duration;
    public Vibrator v;
    public MotionActivity parent;
    public CountDownTimer count;
    public boolean done;



    public AlarmRoutine(Context c, Activity a, MotionActivity ma)
    {
        parent = ma;
        song = MediaPlayer.create(c, R.raw.newsong);
        tick = MediaPlayer.create(c, R.raw.tick);
        v = (Vibrator)c.getSystemService(Context.VIBRATOR_SERVICE);
        done = true;


    }


    public void tick()
    {
        count =  new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                vibrate(10000);
                stopTick();
                play();
                parent.sensorMan.unregisterListener(parent);

                parent.startActivity(new Intent(parent, SpeechActivity.class));
                cancel();
            }
        }.start();
        tick.start();
    }

    public void stopTick()
    {
        count.cancel();
        tick.pause();
    }
    public void play()
    {

        song.start();

    }

    public void vibrate(int n)
    {
        v.vibrate(n);
    }

    public void stop()
    {
        song.stop();
    }
}

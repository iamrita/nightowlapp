package tayz.amrita.com.cuhack;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class SpeechActivity extends ActionBarActivity {

    public CheckList c;
    TextView v;


    protected static final int REQUEST_OK = 1;
    public static final String TAG = "Talk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        v = (TextView)findViewById(R.id.textView2);
        c = new CheckList();
        String s = "";
        for (int i = 0; i < CheckList.items.size(); i++) {
            s += CheckList.items.get(i) + "\n";
        }
        v.setText(s);
    }

    public void goWalk(View view) {
       // startActivity(new Intent(SpeechActivity.this, WalkActivity.class));
    }
    public void goAction(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(i, REQUEST_OK);
            System.out.println("started activity");
        } catch (Exception e) {
            Log.v(TAG, "Exception in calling speech recognizer");
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        TextView view = (TextView)findViewById(R.id.textView2);

            ArrayList<String> things = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.v("in the activity result", "av");
            Log.v(TAG, things.get(0));
            view.setText(things.get(0));

        String s= "";
        for (int i = 0; i < CheckList.items.size(); i++) {
            s += "I have to" + CheckList.items.get(i) + "\n";
        }

       if (things.get(0).equals(s)) {
           startActivity(new Intent(SpeechActivity.this, WalkActivity.class));
           v.setText("Nice job! Hit next");
       } else {

       }

        startActivity(new Intent(SpeechActivity.this, WalkActivity.class));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speech, menu);
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

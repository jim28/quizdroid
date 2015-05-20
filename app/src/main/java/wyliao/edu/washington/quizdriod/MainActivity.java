package wyliao.edu.washington.quizdriod;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    AlarmManager am;
    PendingIntent alarmIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //set alarm
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);





        BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(MainActivity.this,sharedPrefs.getString("urlDld","http://tednewardsandbox.site44.com/questions.json"),Toast.LENGTH_SHORT).show();
                int interval = Integer.parseInt(sharedPrefs.getString("time","1"))*1000*8;
                am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+3000,interval,alarmIntent);

            }
        };


        registerReceiver(alarmReceiver, new IntentFilter("wyliao.edu.washington.quizdriod"));

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent();
        intent.setAction("wyliao.edu.washington.quizdriod");
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent,0);
        int interval = Integer.parseInt(sharedPrefs.getString("time","1"))*1000*8;
        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+3000,interval,alarmIntent);





        TableLayout topicTable = (TableLayout)findViewById(R.id.table);
        //set cata name
        QuizApp quizApp = (QuizApp) getApplication();
        String[] topicsStrArray = quizApp.getAllTopics();


        TextView row1 = (TextView)findViewById(R.id.math);
        row1.setText(topicsStrArray[0]);

        TextView row2 = (TextView) findViewById(R.id.physics);
        row2.setText(topicsStrArray[1]);

        TextView row3 = (TextView) findViewById(R.id.marvel);
        row3.setText(topicsStrArray[2]);




        for(int i=0;i>-1;i++) {
            View row = (View)topicTable.getChildAt(i);


            if(row==null)
                break;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View textView =((ViewGroup)v).getChildAt(0);


                    Intent next = new Intent(MainActivity.this,NextActivity.class);
                    Log.v("WenIDShit",String.valueOf(textView.getId()));
                    next.putExtra("topic",textView.getId());
                    startActivity(next);
                    finish();
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Preference Setting
        if (id == R.id.Preferences) {
            Intent i = new Intent(getApplicationContext(), UserSettingActivity.class);
            startActivityForResult(i, 1);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            displayUserSettings();
        }
    }

    private void displayUserSettings()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Toast.makeText(MainActivity.this,sharedPrefs.getString("urlDld","http://tednewardsandbox.site44.com/questions.json"),Toast.LENGTH_SHORT).show();


    }
}

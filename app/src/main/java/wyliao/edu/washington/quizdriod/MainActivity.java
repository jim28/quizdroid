package wyliao.edu.washington.quizdriod;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.audiofx.BassBoost;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.Settings;
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
import android.telephony.TelephonyManager;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.net.NetworkInfo;
import android.content.DialogInterface;


public class MainActivity extends ActionBarActivity {

    AlarmManager am;
    PendingIntent alarmIntent = null;
    private DownloadManager dm;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateData();
        Log.i("Wen","start MainActivity");

        //Register Receiver for notice of "Download has complete"
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downLoadCmpReceiver, filter);



        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null == activeNetwork) {
            Toast.makeText(this, "no access to the Internet", Toast.LENGTH_LONG);

            boolean airPlaneMode = Settings.Global.getInt(this.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
            if (airPlaneMode) {
                AlertDialog.Builder alertDialogBuider = new AlertDialog.Builder(this);
                alertDialogBuider.setTitle("AirPlaneMode");
                alertDialogBuider
                        .setMessage("Do you want to turn AirPlane mode off ?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                            }


                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }

                                }
                        );
                AlertDialog alertDialog = alertDialogBuider.create();
                alertDialog.show();

            }

            if (!isMobileAvailable(this)) {
                Toast.makeText(this,"No signal ~",Toast.LENGTH_LONG).show();
            }

        }




    }



    BroadcastReceiver downLoadCmpReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {

            String action = intent.getAction();

            dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

            Log.i("MyApp BroadcastReceiver", "onReceive of registered download reciever");

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                Log.i("MyApp BroadcastReceiver", "download complete!");
                long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

                // if the downloadID exists
                if (downloadID != 0) {

                    // Check status
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadID);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        Log.d("DM Sample", "Status Check: " + status);
                        switch (status) {
                            case DownloadManager.STATUS_PAUSED:
                            case DownloadManager.STATUS_PENDING:
                            case DownloadManager.STATUS_RUNNING:
                                break;
                            case DownloadManager.STATUS_SUCCESSFUL:
                                // The download-complete message said the download was "successfu" then run this code
                                ParcelFileDescriptor file;
                                StringBuffer strContent = new StringBuffer("");

                                try {
                                    // Get file from Download Manager (which is a system service as explained in the onCreate)
                                    file = dm.openDownloadedFile(downloadID);
                                    FileInputStream fis = new FileInputStream(file.getFileDescriptor());

                                    // YOUR CODE HERE [convert file to String here]
                                    byte[] buffer = new byte[1024];
                                    int n;

                                    while((n=fis.read(buffer)) != -1) {
                                        strContent.append(new String(buffer, 0, n));
                                    }

                                    QuizApp quizApp = (QuizApp)getApplication();
                                    quizApp.writeToFile(strContent.toString());
                                    quizApp.loadData();
                                    updateData();

                                    // YOUR CODE HERE [write string to data/data.json]
                                    //      [hint, i wrote a writeFile method in MyApp... figure out how to call that from inside this Activity]

                                    // convert your json to a string and echo it out here to show that you did download it



                                    /*

                                    String jsonString = ....myjson...to string().... chipotle burritos.... blah
                                    Log.i("MyApp - Here is the json we download:", jsonString);

                                    */

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case DownloadManager.STATUS_FAILED:
                                Toast.makeText(context, "download fail !!!!", Toast.LENGTH_LONG).show();

                                DownloadService.startOrStopAlarm(context, false, 0);



                                AlertDialog.Builder alertDialogBuider = new AlertDialog.Builder(context);
                                alertDialogBuider.setTitle("Download Fail !");
                                alertDialogBuider
                                        .setMessage("Do you want to retry download again ?")
                                        .setCancelable(false)
                                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                                                int time = Integer.parseInt(sharedPrefs.getString("time","10000"));
                                                DownloadService.startOrStopAlarm(context, true, time);
                                            }


                                        })
                                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        finish();
                                                    }

                                                }
                                        );
                                AlertDialog alertDialog = alertDialogBuider.create();
                                alertDialog.show();




                                // YOUR CODE HERE! Your download has failed! Now what do you want it to do? Retry? Quit application? up to you!
                                break;
                        }
                    }
                }
            }
        }


//        Toast.makeText(MainActivity.this,sharedPrefs.getString("urlDld","http://tednewardsandbox.site44.com/questions.json"),Toast.LENGTH_SHORT).show();
//
//        int interval = Integer.parseInt(sharedPrefs.getString("time", "1")) * 1000 * 8;
//        am.setRepeating(AlarmManager.RTC,System.currentTimeMillis()+3000,interval,alarmIntent);
//
//
//        registerReceiver(alarmReceiver, new IntentFilter("wyliao.edu.washington.quizdriod");
//
//
//
//        am=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent = new Intent();
//        intent.setAction("wyliao.edu.washington.quizdriod");
//        alarmIntent=PendingIntent.getBroadcast(this,0,intent,0);
//        int interval = Integer.parseInt(sharedPrefs.getString("time", "1")) * 1000 * 8;
//        am.setRepeating(AlarmManager.RTC,System.currentTimeMillis()+3000,interval,alarmIntent);

    };







    public void updateData(){
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

            Log.i("Wen","update");

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


    public static Boolean isMobileAvailable(Context appcontext) {
        TelephonyManager tel = (TelephonyManager) appcontext.getSystemService(Context.TELEPHONY_SERVICE);

        return ((tel.getNetworkOperator() != null && tel.getNetworkOperator().equals("")) ? false : true);
    }

}



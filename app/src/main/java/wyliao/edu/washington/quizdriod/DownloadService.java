package wyliao.edu.washington.quizdriod;

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by MSR-2 on 5/21/2015.
 */
public class DownloadService extends IntentService {


    private DownloadManager dm;
    private long enqueue;
    public static final int Wen_ALARM = 731;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public void onCreate() { super.onCreate(); }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Log.i("DownloadService", "entered onHandleIntent()");
        // Hooray! This method is called where the AlarmManager shouldve started the download service and we just received it here!

        // Specify the url you want to download here
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String url = sharedPrefs.getString("urlDld","http://tednewardsandbox.site44.com/questions.json");


        Log.i("DownloadService", "should be downloading here");

        // Star the download
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        enqueue = dm.enqueue(request);


    }

    public static void startOrStopAlarm(Context context, boolean on, int time) {
        Log.i("DownloadService", "startOrStopAlarm on = " + on);



        Intent alarmReceiverIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Wen_ALARM, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        if (on) {
            Log.i("DownloadService", "setting alarm to " + time);
            // Start the alarm manager to repeat
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time, pendingIntent);
        }
        else {
            manager.cancel(pendingIntent);
            pendingIntent.cancel();
            Log.i("DownloadService", "Stopping alarm");
        }
    }



}

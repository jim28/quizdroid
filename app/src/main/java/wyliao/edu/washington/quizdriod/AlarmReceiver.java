package wyliao.edu.washington.quizdriod;

import android.content.BroadcastReceiver;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by MSR-2 on 5/22/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver","entered onReceive");

        Intent downloadServiceIntent = new Intent(context, DownloadService.class);
        context.startService(downloadServiceIntent);

    }
}

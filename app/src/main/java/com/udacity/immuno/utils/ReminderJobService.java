package com.udacity.immuno.utils;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.udacity.immuno.R;
import com.udacity.immuno.activities.MainActivity;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

/**
 * Created by sengopal on 11/11/15.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ReminderJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        VaccineData vaccineData = DBHelper.checkForTodaysVaccines();
        if(null!=vaccineData) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Immunization Reminder")
                    .setContentText(vaccineData.getCasualName());
            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
            return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

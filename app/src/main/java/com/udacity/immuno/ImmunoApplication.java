package com.udacity.immuno;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.udacity.immuno.database.DBHelper;

/**
 * Created by sengopal on 11/9/15.
 */
public class ImmunoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        ActiveAndroid.initialize(this);
        //To Initialize
        DBHelper.setupDemoUserInfo();
        DBHelper.getPrimaryUser();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

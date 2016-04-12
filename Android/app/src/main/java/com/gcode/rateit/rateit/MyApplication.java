package com.gcode.rateit.rateit;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}

package com.example.pritam.init;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class SalseApplication extends Application {
    private static Context APP_CONTEXT=null;

    @Override
    public void onCreate() {
        super.onCreate();
        SalseApplication.APP_CONTEXT = getApplicationContext();
    }

    public static Activity getAppContext() {
        return (Activity) APP_CONTEXT;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

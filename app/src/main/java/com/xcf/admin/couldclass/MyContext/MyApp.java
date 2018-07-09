package com.xcf.admin.couldclass.MyContext;

import android.app.Application;
import android.content.Context;

import io.vov.vitamio.Vitamio;

/**
 * Created by Admin on 2018-04-03.
 */

public class MyApp extends Application {
    private static Context context;

    //返回
    public static Context getContextObject() {
        return context;
    }

    @Override
    public void onCreate() {
        context = getApplicationContext();
        Vitamio.isInitialized(this);
        super.onCreate();
    }
}

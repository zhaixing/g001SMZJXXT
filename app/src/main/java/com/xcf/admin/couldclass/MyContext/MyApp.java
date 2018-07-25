package com.xcf.admin.couldclass.MyContext;

import android.app.Application;
import android.content.Context;

import com.xcf.admin.couldclass.Entity.examroom.Appques;

import java.util.ArrayList;
import java.util.List;

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


    public static Appques appquesmain;//具体的题
    public static int questionsum;//题目的总数量
    public static String papername;//考场的名字
    public static String roomid;//(考场的id)
    public static List<Integer> falselist = new ArrayList<>();
}

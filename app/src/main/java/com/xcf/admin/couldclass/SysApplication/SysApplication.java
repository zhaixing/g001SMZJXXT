package com.xcf.admin.couldclass.SysApplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity的启动和销毁
 * Created by xkc on 12/11/15.
 */
public class SysApplication extends AppCompatActivity {
    private List<Activity> mList = new ArrayList<>();
    private static SysApplication instance;

    private SysApplication() {
    }

    public static synchronized SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }

    //将启动的进程添加进入list中
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    //将list中的activity全部销毁
    public void exit() {
        for (Activity activity : mList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }


    //杀进程
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
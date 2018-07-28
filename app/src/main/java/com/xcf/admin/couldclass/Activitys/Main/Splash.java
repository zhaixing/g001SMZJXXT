package com.xcf.admin.couldclass.Activitys.Main;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.xcf.admin.couldclass.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_splash);

        Thread myThread = new Thread(){
            // 创建子线程
            public void run(){
                try{
                    sleep(2000);//程序休眠5秒
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("com.xcf.admin.couldclass", "com.xcf.admin.couldclass.Activitys.Loginyhs.LoginActivity");
//或者intent.setClassName("包名","包名.类名");
                    intent.setComponent(comp);
                    startActivity(intent);

//                    Intent it = new Intent(getApplicationContext(),FragmentOne_yhs.class);// 启动登录页面
//                    startActivity(it);
                    finish();//关闭当前活动
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }
}

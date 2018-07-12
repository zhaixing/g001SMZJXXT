package com.xcf.admin.couldclass.Activitys.Personal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.SysApplication.SysApplication;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {


    Button logoff1;
    Button logoff2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        SetListener();
        SysApplication.getInstance().addActivity(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoff1:
            case R.id.logoff2: {
                SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                SysApplication.getInstance().exit();
                Intent intent = new Intent();
                ComponentName comp = new ComponentName("com.xcf.admin.couldclass", "com.xcf.admin.couldclass.Activitys.Loginyhs.LoginActivity");
//或者intent.setClassName("包名","包名.类名");
                intent.setComponent(comp);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    private void SetListener() {
        logoff1 = findViewById(R.id.logoff1);
        logoff2 = findViewById(R.id.logoff2);
        logoff1.setOnClickListener(this);
        logoff2.setOnClickListener(this);
    }

}

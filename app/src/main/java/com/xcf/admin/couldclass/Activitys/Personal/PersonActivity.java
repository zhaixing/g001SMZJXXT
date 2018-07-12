package com.xcf.admin.couldclass.Activitys.Personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.SysApplication.SysApplication;

import java.util.Objects;

public class PersonActivity extends AppCompatActivity {

    android.support.v7.app.ActionBar actionbar;
    TextView name;
    TextView zhanghao;
    TextView idcard;
    TextView phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        name = findViewById(R.id.tv_user_name);
        zhanghao = findViewById(R.id.tv_user_zhanghao);
        idcard = findViewById(R.id.tv_user_idcard);
        phone_number = findViewById(R.id.tv_user_phoneno);
        name.setText(sp.getString("username", null));
        zhanghao.setText(sp.getString("zhanghao", null));
        idcard.setText(sp.getString("idcard", null));
        phone_number.setText(sp.getString("phonenumber", null));
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
}

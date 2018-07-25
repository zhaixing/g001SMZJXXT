package com.xcf.admin.couldclass.Activitys.Loginyhs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.converfactory.StringConverterFactory;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.login.loginuser;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.MyContext.ClientContext;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.SysApplication.SysApplication;
import com.xcf.admin.couldclass.handle.persistentcookiejar.PersistentCookieJar;
import com.xcf.admin.couldclass.handle.persistentcookiejar.cache.SetCookieCache;
import com.xcf.admin.couldclass.handle.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Gson gson;
    Button login_btn;
    Button register;
    TextView Usercode;
    TextView pwd;

    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().hide();//隐藏Actionbar
        SetListener();//
        InitHttp();
        SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String token = sp.getString("token", null);
        SysApplication.getInstance().addActivity(this);
        try {
            if (token != null) {
                UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                Call<loginuser> call = u.Login(null, null, token);
                loginin(call);
            }
        } catch (Exception e) {
        }

        avi = findViewById(R.id.avi);
        avi.show();
    }


    private void SetListener() {
        login_btn = findViewById(R.id.btn_login_register);//登录
        register = findViewById(R.id.switch_button);//注册
        Usercode = findViewById(R.id.usercode);
        pwd = findViewById(R.id.pwd);
        login_btn.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_register: {
                UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                Call<loginuser> call = u.Login(Usercode.getText().toString(), pwd.getText().toString(), "");
                loginin(call);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void InitHttp() {
        HttpHelper.getInstance().setCookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.getContextObject())));
        HttpHelper.getInstance().getCookieJar().clear();
        HttpHelper.getInstance().setOkHttpClient(new OkHttpClient.Builder()
                .cookieJar(HttpHelper.getInstance().getCookieJar())
                .build());

        HttpHelper.getInstance().setRetrofit(new Retrofit.Builder()
                .baseUrl(HttpHelper.getInstance().path)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpHelper.getInstance().getOkHttpClient())
                .build());

        HttpHelper.getInstance().setRetrofitStr(new Retrofit.Builder()
                .baseUrl(HttpHelper.getInstance().path)
                .addConverterFactory(StringConverterFactory.create())
                .client(HttpHelper.getInstance().getOkHttpClient())
                .build());
    }

    public void loginin(Call<loginuser> call) {
        call.enqueue(new Callback<loginuser>() {
            @Override
            public void onResponse(Call<loginuser> call, Response<loginuser> response) {
                Log.d(this.getClass().toString(), "onResponse: " + response.body());
                //System.out.println(response.body().getUsr().getUser_Id());
                if (response.body().getbool().equals("success")) {
                    //System.out.println("ID"+response.body().getUsr().getUser_Id());
                    //将信息保存到本地
                    User user = response.body().getUser();
                    SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userid", user.getUser_Id().toString());
                    editor.putString("username", user.getUser_name());
                    editor.putString("zhanghao", user.getUser_Code().toString());
                    editor.putString("idcard", user.getId_card());
                    editor.putString("phonenumber", user.getPhone_number());
                    editor.putString("org", response.body().getOrg());
                    editor.putString("major", response.body().getMajor());
                    editor.putString("xianzhi", response.body().getXianzhi());
                    editor.putString("token", user.getToken());
                    editor.putString("imagehead", ClientContext.My_ip + user.getUser_Image_Head());
                    editor.commit();
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("com.xcf.admin.couldclass", "com.xcf.admin.couldclass.Activitys.Main.MainActivity");
//或者intent.setClassName("包名","包名.类名");
                    intent.setComponent(comp);
                    startActivity(intent);
                    finish();
                } else if (response.body().getbool().equals("false")) {
                    Toast.makeText(LoginActivity.this, MessageContext.USER_PWD_ERROR, Toast.LENGTH_SHORT).show();
                } else if (response.body().getbool().equals("repeat")) {
                    Toast.makeText(LoginActivity.this, MessageContext.LOGIN_ERROR, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginuser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
            }
        });

    }
}

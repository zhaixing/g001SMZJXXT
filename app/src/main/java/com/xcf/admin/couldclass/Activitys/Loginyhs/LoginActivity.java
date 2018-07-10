package com.xcf.admin.couldclass.Activitys.Loginyhs;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.converfactory.StringConverterFactory;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;
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


    Button login_btn;
    Button register;
    TextView Usercode;
    TextView pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().hide();//隐藏Actionbar
        SetListener();//
        InitHttp();
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
                System.out.println("pos2");
                Log.i(this.getClass().toString(), "onClick: pos1");
                UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                Call<String> call = u.Login(Usercode.getText().toString(), pwd.getText().toString(), "");
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(this.getClass().toString(), "onResponse: " + response.body());
                        if (response.body().equals("success")) {
                            Intent intent = new Intent();
                            ComponentName comp = new ComponentName("com.xcf.admin.couldclass", "com.xcf.admin.couldclass.Activitys.Main.MainActivity");
//或者intent.setClassName("包名","包名.类名");
                            intent.setComponent(comp);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, MessageContext.USER_PWD_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
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
}

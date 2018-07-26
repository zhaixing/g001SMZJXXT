package com.xcf.admin.couldclass.Activitys.Loginyhs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.login.loginuser;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.MyContext.ClientContext;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_reg_usercode;
    private EditText et_pwd;
    private EditText et_pwd_confirm;
    private EditText et_reg_name;
    private Button btn_register;
    private Button btn_login_return;
    private AVLoadingIndicatorView avi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        avi2 = findViewById(R.id.avi2);

        et_reg_usercode = findViewById(R.id.reg_usercode);
        et_pwd = findViewById(R.id.reg_pwd);
        et_pwd_confirm = findViewById(R.id.reg_pwd_confirm);
        et_reg_name = findViewById(R.id.reg_name);
        btn_register = findViewById(R.id.btn_register);
        btn_login_return = findViewById(R.id.btn_login_return);

        et_reg_usercode.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
        et_pwd_confirm.setOnClickListener(this);
        et_reg_name.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_login_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_usercode:{

                break;
            }
            case R.id.reg_pwd:{
                break;
            }
            case R.id.reg_pwd_confirm:{
                break;
            }
            case R.id.reg_name:{
                break;
            }
            case R.id.btn_register:{
                // 判断是否为空
                if (isNull())
                {
                    // 判断是否注册成功
                    UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                    avi2.smoothToShow();
                    Call<String> call = u.Register(et_reg_usercode.getText().toString(),et_pwd.getText().toString(),et_reg_name.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.e("", "onResponse: "+response.body() );
                            if (response.body().toString().equals( "true"))
                            {
                                // 成功
                                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();

                                // 跳转到主页
                                UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                                Call<loginuser> call1 = u.Login(et_reg_usercode.getText().toString(), et_pwd.getText().toString(), null);
                                loginin(call1);
                                avi2.smoothToHide();
                            }
                            else
                            {
                                // 成功
                                Toast.makeText(getApplicationContext(),"账号已存在",Toast.LENGTH_SHORT).show();
                                avi2.smoothToHide();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"注册失败,网络异常",Toast.LENGTH_SHORT).show();
                            avi2.smoothToHide();
                        }
                    });
                    avi2.smoothToHide();
                }

                break;
            }
            case R.id.btn_login_return:{
                Intent intent =new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void loginin(Call<loginuser> call) {
        avi2.smoothToShow();
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
                    avi2.smoothToHide();
                    startActivity(intent);
                    finish();
                } else if (response.body().getbool().equals("false")) {
                    avi2.smoothToHide();
                    Toast.makeText(RegisterActivity.this, MessageContext.USER_PWD_ERROR, Toast.LENGTH_SHORT).show();
                } else if (response.body().getbool().equals("repeat")) {
                    avi2.smoothToHide();
                    Toast.makeText(RegisterActivity.this, MessageContext.LOGIN_ERROR, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginuser> call, Throwable t) {
                avi2.smoothToHide();
                Toast.makeText(RegisterActivity.this, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isNull()
    {
        if (et_reg_usercode.getText().length() == 0)
        {
            Toast.makeText(this,"手机号不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(et_pwd.getText().length() == 0)
        {
            Toast.makeText(this,"密码不得为空",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if (et_pwd_confirm.getText().length()==0)
        {
            Toast.makeText(this,"确认密码不得为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!et_pwd_confirm.getText().toString().equals(et_pwd.getText().toString()))
        {
            Toast.makeText(this,"请输入相同的密码",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(et_reg_name.getText().length() == 0)
        {
            Toast.makeText(this,"姓名不得为空",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
}

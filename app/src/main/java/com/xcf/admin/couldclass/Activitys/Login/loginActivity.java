package com.xcf.admin.couldclass.Activitys.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login_btn;
    TextView name;
    TextView pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();隐藏Actionbar
        System.out.println("2222");
        SetListener();//
    }

    private void SetListener() {
        login_btn = findViewById(R.id.btn_login);
        name = findViewById(R.id.txt_username);
        name.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        pwd = findViewById(R.id.txt_pwd);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                Call<String> call = u.Login(name.getText().toString(), pwd.getText().toString(), "");
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(this.getClass().toString(), "onResponse: " + response.body());
                        if (response.body().equals("success")) {
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, MessageContext.USER_PWD_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(loginActivity.this, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            }
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }

}

package com.xcf.admin.couldclass.Activitys.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_reg_reg;
    TextView text_no;
    TextView text_name;
    TextView text_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SetLins();
    }

    private void SetLins() {
        btn_reg_reg = findViewById(R.id.btn_reg_reg);
        text_no = findViewById(R.id.txt_reg_no);
        text_name = findViewById(R.id.txt_reg_name);
        text_pwd = findViewById(R.id.txt_reg_pwd);

        btn_reg_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg_reg: {
                if (text_no.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入帐号！", Toast.LENGTH_LONG);
                    return;
                }
                if (text_pwd.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入密码！", Toast.LENGTH_LONG);
                    return;
                }
                User user = new User();
                user.setUser_Code(Long.parseLong(text_no.getText().toString()));
                user.setUser_name(text_name.getText().toString());
                user.setUser_Pwd(text_pwd.getText().toString());
                user.setUser_Image_Idcard("");

                Gson gson = new Gson();

                UserService userService = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                Call<String> call = userService.SaveUserApp(gson.toJson(user));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null && response.body().equals("success")) {
//                            Intent intent = new Intent(RegisterActivity.this, FinishUserActivity.class);
                            finish();
//                            startActivity(intent);

                        } else {
                            Toast.makeText(RegisterActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        }
    }
}

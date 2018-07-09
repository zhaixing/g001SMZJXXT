package com.xcf.admin.couldclass.Activitys.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.Activitys.Login.FinishUserActivity;
import com.xcf.admin.couldclass.Activitys.Login.RegisterActivity;
import com.xcf.admin.couldclass.Activitys.Login.loginActivity;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentThree extends Fragment implements View.OnClickListener {

    Button btn_openLogin;
    TextView txt_info;
    Button btn_reg;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SetListener();
    }

    @Override
    public void onResume() {
        CheckLogin();
        super.onResume();
    }

    private void SetListener() {
        btn_openLogin = getActivity().findViewById(R.id.btn_openLogin);
        txt_info = getActivity().findViewById(R.id.txt_info);
        btn_reg = getActivity().findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);
        btn_openLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_openLogin: {

                if (btn_openLogin.getText().equals("退出")) {
                    UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
                    Call<String> call = u.LogOut("ss");
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            btn_openLogin.setText("登录");
                            txt_info.setText("姓名：");
                            btn_reg.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                } else {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
                break;
            }
            case R.id.btn_reg: {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);

            }
        }
    }

    private void CheckLogin() {

        UserService u = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
        Call<User> call = u.CheckLogin();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(this.getClass().toString(), "onResponse: " + response.body());
                user = response.body();

                if (user != null && user.getError() == null) {
                    btn_openLogin.setText("退出");
                    txt_info.setText("姓名：" + user.getUser_name() + "");
                    btn_reg.setVisibility(View.GONE);
                } else if (user != null && user.getError().equals("您已退出，请重新登录。")) {
                    btn_openLogin.setText("登录");
                    txt_info.setText("");
                    txt_info.setText("姓名：");
                    btn_reg.setVisibility(View.VISIBLE);
                } else if (user != null && user.getError().equals("请完善用户信息!")) {
                    btn_openLogin.setText("退出");
                    txt_info.setText("姓名：" + user.getUser_name() + "");
                    btn_reg.setVisibility(View.GONE);
                    Intent intent = new Intent(getActivity(), FinishUserActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

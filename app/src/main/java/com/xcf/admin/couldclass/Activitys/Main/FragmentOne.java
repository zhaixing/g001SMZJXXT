package com.xcf.admin.couldclass.Activitys.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcf.admin.couldclass.Activitys.Exam.ExamRoomActivity;
import com.xcf.admin.couldclass.Activitys.Live.LiveActivity;
import com.xcf.admin.couldclass.Dao.PaperService;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.result.ResultPaper;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentOne extends Fragment implements View.OnClickListener {

    //    Button btn_http;
    Button btn_marking;
    Button btn_teach;
    PaperService paperService;
    UserService userService1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SetListener();
    }

    private void SetListener() {

//        btn_http = getActivity().findViewById(R.id.btn_httptest);
        btn_marking = getActivity().findViewById(R.id.btn_marking);
        btn_teach = getActivity().findViewById(R.id.btn_teach);
        paperService = HttpHelper.getInstance().getRetrofit().create(PaperService.class);
        userService1 = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);

        btn_teach.setOnClickListener(this);
        btn_marking.setOnClickListener(this);
//        btn_http.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_marking: {
                Intent intent = new Intent(getActivity(), ExamRoomActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_teach: {
                Intent intent = new Intent(getActivity(), LiveActivity.class);
                startActivity(intent);
                break;
            }

        }
    }


    private void sendRequestWithHttpURLConnection() {
        Call<List<User>> call = userService1.GetUsers(0, 10);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User u : response.body()) {
                    Log.i("这里在list中", "onResponse: " + u.getUser_name());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


        Call<ResultPaper> call1 = paperService.GetRamd();
        call1.enqueue(new Callback<ResultPaper>() {
            @Override
            public void onResponse(Call<ResultPaper> call, Response<ResultPaper> response) {
                Log.d("Server", response.body().getEpu_Name());
                Gson gson = new Gson();
                Log.d("Server", gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<ResultPaper> call, Throwable t) {
                Log.e("Server", "onFailure: 请求失败");
            }
        });
    }


    private void showResponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                Log.d("返回信息", response);
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

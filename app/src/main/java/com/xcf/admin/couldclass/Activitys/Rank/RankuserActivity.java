package com.xcf.admin.couldclass.Activitys.Rank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xcf.admin.couldclass.Dao.RankService;
import com.xcf.admin.couldclass.MyContext.ClientContext;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankuserActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name;
    TextView myorg;
    TextView mymajor;
    TextView myposition;
    String userid;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_me);
        //getSupportActionBar().hide();//隐藏Actionbar
        getdata();
    }

    protected void getdata() {
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        System.out.println(userid);
        imageView = findViewById(R.id.default_user_image1);
        name = findViewById(R.id.btn_user);
        myorg = findViewById(R.id.view_my_org);
        mymajor = findViewById(R.id.view_my_major);
        myposition = findViewById(R.id.view_my_position);
        view = findViewById(R.id.view_setting);
        view.setVisibility(View.GONE);
        RankService rankService = HttpHelper.getInstance().getRetrofitStr().create(RankService.class);
        Call<List> call = rankService.Getrankuser(userid);
        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                System.out.println("接收榜单个人信息成功");
                Glide.with(getApplicationContext()).load(ClientContext.My_ip + response.body().get(1)).override(150, 150).diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
                name.setText("姓名：" + response.body().get(0));
                if (response.body().get(3) != null) {
                    myorg.setText(response.body().get(3) + "");
                }
                if (response.body().get(2) != null) {
                    mymajor.setText(response.body().get(2) + "");
                }
                if (response.body().get(4) != null) {
                    myposition.setText(response.body().get(4) + "");
                }
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {

            }
        });
    }
}

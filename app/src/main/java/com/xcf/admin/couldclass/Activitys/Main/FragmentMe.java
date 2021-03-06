package com.xcf.admin.couldclass.Activitys.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xcf.admin.couldclass.Activitys.Personal.PersonActivity;
import com.xcf.admin.couldclass.Activitys.Personal.SettingActivity;
import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.SysApplication.SysApplication;

public class FragmentMe extends Fragment implements View.OnClickListener {

    RelativeLayout SETTING;
    RelativeLayout USER;
    TextView myorg;
    TextView mymajor;
    TextView myposition;
    TextView name;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        SysApplication.getInstance().addActivity(this.getActivity());
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SetListener();
    }

    private void SetListener() {
        //btn_user = getActivity().findViewById(R.id.btn_user);
        //btn_user.setOnClickListener(this);

        SETTING = getActivity().findViewById(R.id.view_setting);
        SETTING.setOnClickListener(this);

        USER = getActivity().findViewById(R.id.user_not_login);
        USER.setOnClickListener(this);
        imageView = getActivity().findViewById(R.id.default_user_image1);
        SharedPreferences sp = getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        Glide.with(this).load(sp.getString("imagehead", null)).override(150, 150) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(imageView);
        myorg = getActivity().findViewById(R.id.view_my_org);
        mymajor = getActivity().findViewById(R.id.view_my_major);
        myposition = getActivity().findViewById(R.id.view_my_position);
        name = getActivity().findViewById(R.id.btn_user);
        name.setText("姓名:  " + sp.getString("username", null));
        myorg.setText(sp.getString("org", null));
        mymajor.setText(sp.getString("major", null));
        myposition.setText(sp.getString("xianzhi", null));
        myorg.setText(sp.getString("org", null));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_not_login: {
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.view_setting: {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}

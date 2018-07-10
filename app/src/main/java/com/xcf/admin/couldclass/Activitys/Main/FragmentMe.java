package com.xcf.admin.couldclass.Activitys.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xcf.admin.couldclass.Activitys.Login.PersonActivity;
import com.xcf.admin.couldclass.R;

public class FragmentMe extends Fragment implements View.OnClickListener {

    Button btn_user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SetListener();
    }

    private void SetListener() {
        btn_user = getActivity().findViewById(R.id.btn_user);
        btn_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_user: {
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
            }
        }
    }
}

package com.xcf.admin.couldclass.Activitys.Rank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.xcf.admin.couldclass.R;


public class RankActivity extends AppCompatActivity {


    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_top);
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        //inflater.inflate(R.layout.anctivity_rank_week,tabHost.getTabContentView());
        //inflater.inflate(R.layout.anctivity_rank_month,tabHost.getTabContentView());
        //tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("精选表情").setContent(R.id.zzz));
        // tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("投稿表情").setContent(R.id.aaa));
        setTabHost();
    }

    public void setTabHost() {

    }


}

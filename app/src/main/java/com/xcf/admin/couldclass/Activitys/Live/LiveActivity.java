package com.xcf.admin.couldclass.Activitys.Live;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    WebView webView;
    Button btn_play;
    TextView txt_ipl;
    boolean isFull = true;
    private VideoView mVvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制横屏

        mVvv = findViewById(R.id.vv_main);//实例化
        btn_play = findViewById(R.id.btn_play);
        txt_ipl = findViewById(R.id.txt_ip);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVvv.setVideoURI(Uri.parse("rtmp://" + txt_ipl.getText() + "/live/0"));//设置播放地址
            }
        });


        mVvv.setMediaController(new MediaController(this));

        //设置监听
        mVvv.setOnPreparedListener(this);
        mVvv.setOnErrorListener(this);
        mVvv.setOnCompletionListener(this);

    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mVvv.stopPlayback();
        super.onDestroy();

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(this, "准备好了", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(this, "播放完成", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        mVvv.stopPlayback();
        finish();
    }


}

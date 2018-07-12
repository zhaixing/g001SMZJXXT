package com.xcf.admin.couldclass.Activitys.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.converfactory.StringConverterFactory;
import com.xcf.admin.couldclass.Activitys.Loginyhs.FragmentExam;
import com.xcf.admin.couldclass.MyContext.BottomNavigationViewHelper;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.handle.persistentcookiejar.PersistentCookieJar;
import com.xcf.admin.couldclass.handle.persistentcookiejar.cache.SetCookieCache;
import com.xcf.admin.couldclass.handle.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {


    BottomNavigationView navigation;
    private ViewPager myViewPager;
    private List<Fragment> fragments;
    private TabFragmentPagerAdapter adapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    myViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_friends:
                    myViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_exam:
                    myViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    myViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        myViewPager = findViewById(R.id.myViewPager);
        //把Fragment添加到List集合里面
        fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentExam());
        fragments.add(new FragmentMe());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面

        navigation.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        myViewPager.addOnPageChangeListener(this);
        InitHttp();
    }

    public void InitHttp() {
        HttpHelper.getInstance().setCookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.getContextObject())));
        HttpHelper.getInstance().getCookieJar().clear();
        HttpHelper.getInstance().setOkHttpClient(new OkHttpClient.Builder()
                .cookieJar(HttpHelper.getInstance().getCookieJar())
                .build());

        HttpHelper.getInstance().setRetrofit(new Retrofit.Builder()
                .baseUrl(HttpHelper.getInstance().path)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpHelper.getInstance().getOkHttpClient())
                .build());

        HttpHelper.getInstance().setRetrofitStr(new Retrofit.Builder()
                .baseUrl(HttpHelper.getInstance().path)
                .addConverterFactory(StringConverterFactory.create())
                .client(HttpHelper.getInstance().getOkHttpClient())
                .build());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                myViewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_friends:
                myViewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_exam:
                myViewPager.setCurrentItem(2);
                return true;
            case R.id.navigation_me:
                myViewPager.setCurrentItem(3);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_friends);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.navigation_exam);
                break;
            case 3:
                navigation.setSelectedItemId(R.id.navigation_me);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

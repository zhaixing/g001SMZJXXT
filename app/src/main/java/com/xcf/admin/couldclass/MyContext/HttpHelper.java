package com.xcf.admin.couldclass.MyContext;

import com.xcf.admin.couldclass.handle.persistentcookiejar.ClearableCookieJar;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Created by Admin on 2018-04-02.
 */

public class HttpHelper {
    private static HttpHelper singleTonInstance;
    public final String path = "http://192.168.1.53:8081";
    private ClearableCookieJar cookieJar;
    private OkHttpClient okHttpClient;
    private Retrofit retrofitStr;
    private Retrofit retrofit;

    //将构造函数私有化
    private HttpHelper() {
    }


    //Double Check Lock
    public static HttpHelper getInstance() {
        if (singleTonInstance == null) {
            synchronized (HttpHelper.class) {
                if (singleTonInstance == null) {
                    singleTonInstance = new HttpHelper();
                }
            }
        }
        return singleTonInstance;
    }


    public Retrofit getRetrofitStr() {
        return retrofitStr;
    }

    public void setRetrofitStr(Retrofit retrofitStr) {
        this.retrofitStr = retrofitStr;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public String getPath() {
        return path;
    }

    public ClearableCookieJar getCookieJar() {
        return cookieJar;
    }

    public void setCookieJar(ClearableCookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}

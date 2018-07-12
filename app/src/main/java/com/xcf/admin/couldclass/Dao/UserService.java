package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.login.loginuser;
import com.xcf.admin.couldclass.Entity.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Admin on 2018-04-02.
 */

public interface UserService {
    //获取豆瓣Top250 榜单
    @GET("/viewpages/jsons.do")
    Call<List<User>> GetUsers(@Query("offset") int start, @Query("reqSize") int count);

    @GET("/viewpages/json.do")
    Call<User> GetUser();

    @GET("/viewpages/json.do")
    Call<String> GetUserStr();

    @GET("/app/login.do")
    Call<loginuser> Login(@Query("usercode") String usercode, @Query("pwd") String pwd, @Query("token") String Token);

    @GET("/app/logout.do")
    Call<String> LogOut(@Query("usercode") String usercode);

    @GET("/app/check.do")
    Call<User> CheckLogin();

    @FormUrlEncoded
    @POST("/app/user/register.do")
    Call<String> SaveUserApp(@Field("json") String json);

    @GET("/app/user/finish.do")
    Call<String> FinishUserApp(@Query("name") String name, @Query("orgid") String orgid);
}

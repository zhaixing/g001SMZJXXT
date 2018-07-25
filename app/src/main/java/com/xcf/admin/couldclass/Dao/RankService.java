package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.rank.rankuserlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RankService {
    @GET("/app/rank/ranklist.do")
    Call<rankuserlist> Getranklist();

    @GET("/app/rank/rankuser.do")
    Call<List> Getrankuser(@Query("userid") String userid);
}

package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.result.ResultQues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface ExamService {

    @GET("/app/exam/list.do")
    Call<String> GetExamRoom();

    @GET("/app/startexam.do")
    Call<List<ResultQues>> StartExam(@Query("examroomid") String examroomid);

    @GET("/app/exam/savesingle.do")
    Call<String> SaveSingle(@Query("idkey") String idkey);

    @FormUrlEncoded
    @POST("/app/exam/saveanswer.do")
    Call<String> SaveALL(@Field("answer") String answer);
}

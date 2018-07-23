package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.examroom.Appques;
import com.xcf.admin.couldclass.Entity.examroom.ListExamRoom;
import com.xcf.admin.couldclass.Entity.result.ResultQues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExamServiceyhs {

    @GET("/app/exam/listyhs.do")
    Call<ListExamRoom> GetExamRoom1(@Query("token") String token);

    @GET("/app/exam/exampapaeryhs.do")
    Call<Appques> Getpaper(@Query("userid") String userid, @Query("roomid") String roomid);

    @GET("/app/exam/examansweryhs.do")
    Call<String> setselected(@Query("userid") String userid, @Query("roomid") String roomid, @Query("selected") String list, @Query("q_id") String q_id);

    @GET("/app/exam/examendyhs.do")
    Call<List<ResultQues>> examend(@Query("userid") String userid, @Query("roomid") String roomid);
}
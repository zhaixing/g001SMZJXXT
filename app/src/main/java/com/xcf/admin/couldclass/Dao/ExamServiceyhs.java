package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.examroom.Appques;
import com.xcf.admin.couldclass.Entity.examroom.Examend;
import com.xcf.admin.couldclass.Entity.examroom.ListExamRoom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExamServiceyhs {

    @GET("/app/exam/listyhs.do")
    Call<ListExamRoom> GetExamRoom1(@Query("token") String token, @Query("major") String major, @Query("complete") String complete, @Query("timestate") String timestate, @Query("erpapertype") String erpapertype);

    @GET("/app/exam/exampapaeryhs.do")
    Call<Appques> Getpaper(@Query("userid") String userid, @Query("roomid") String roomid);

    @GET("/app/exam/examansweryhs.do")
    Call<String> setselected(@Query("userid") String userid, @Query("roomid") String roomid, @Query("selected") String list, @Query("q_id") String q_id);

    @GET("/app/exam/examendyhs.do")
    Call<Examend> examend(@Query("userid") String userid, @Query("roomid") String roomid);

    @GET("/app/exam/changecomplete.do")
    Call<String> changecomplete(@Query("userid") String userid, @Query("roomid") String roomid);

    @GET("/app/exam/roomadd.do")
    Call<String> roomadd(@Query("token") String token, @Query("roomname") String roomname, @Query("type") String type, @Query("start")
            String start, @Query("end") String end, @Query("snum") String snum
            , @Query("dnum") String dnum, @Query("pnum") String pnum, @Query("erpapertype") String erpapertype);

    @GET("/app/exam/clear.do")
    Call<String> clearpaper(@Query("userid") String userid, @Query("roomid") String roomid);
}

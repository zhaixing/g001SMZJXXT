package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.examroom.Appques;
import com.xcf.admin.couldclass.Entity.examroom.Examend;
import com.xcf.admin.couldclass.Entity.examroom.ListExamRoom;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ExamServiceyhs {

    @FormUrlEncoded
    @POST("/app/exam/listyhs.do")
    Call<ListExamRoom> GetExamRoom1(@Field("token") String token, @Field("major") String major, @Field("complete") String complete, @Field("timestate") String timestate, @Field("erpapertype") String erpapertype);

    @FormUrlEncoded
    @POST("/app/exam/exampapaeryhs.do")
    Call<Appques> Getpaper(@Field("userid") String userid, @Field("roomid") String roomid);

    @FormUrlEncoded
    @POST("/app/exam/examansweryhs.do")
    Call<String> setselected(@Field("userid") String userid, @Field("roomid") String roomid, @Field("selected") String list, @Field("q_id") String q_id);

    @FormUrlEncoded
    @POST("/app/exam/examendyhs.do")
    Call<Examend> examend(@Field("userid") String userid, @Field("roomid") String roomid);

    @FormUrlEncoded
    @POST("/app/exam/changecomplete.do")
    Call<String> changecomplete(@Field("userid") String userid, @Field("roomid") String roomid);

    @FormUrlEncoded
    @POST("/app/exam/roomadd.do")
    Call<String> roomadd(@Field("token") String token, @Field("roomname") String roomname, @Field("type") String type, @Field("start")
            String start, @Field("end") String end, @Field("snum") String snum
            , @Field("dnum") String dnum, @Field("pnum") String pnum, @Field("erpapertype") String erpapertype);

    @FormUrlEncoded
    @POST("/app/exam/clear.do")
    Call<String> clearpaper(@Field("userid") String userid, @Field("roomid") String roomid);
}

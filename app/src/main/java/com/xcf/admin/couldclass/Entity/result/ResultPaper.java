package com.xcf.admin.couldclass.Entity.result;


import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;

public class ResultPaper extends BaseEntity implements Serializable {

    private Long Epu_id;
    private String TimeCreated;
    private String Epu_Name;
    private Long Er_Id;
    private Long User_Id;
    private Long UesrEx_Id;
    private Long Complete_Id;
    private Integer Real_Score;
    private Long Time_Userd;
    private String StartTime;
    private String End_Time;

    public Long getEpu_id() {
        return Epu_id;
    }

    public void setEpu_id(Long epu_id) {
        Epu_id = epu_id;
    }

    public String getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        TimeCreated = timeCreated;
    }

    public String getEpu_Name() {
        return Epu_Name;
    }

    public void setEpu_Name(String epu_Name) {
        Epu_Name = epu_Name;
    }

    public Long getEr_Id() {
        return Er_Id;
    }

    public void setEr_Id(Long er_Id) {
        Er_Id = er_Id;
    }

    public Long getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(Long user_Id) {
        User_Id = user_Id;
    }

    public Long getUesrEx_Id() {
        return UesrEx_Id;
    }

    public void setUesrEx_Id(Long uesrEx_Id) {
        UesrEx_Id = uesrEx_Id;
    }

    public Long getComplete_Id() {
        return Complete_Id;
    }

    public void setComplete_Id(Long complete_Id) {
        Complete_Id = complete_Id;
    }

    public Integer getReal_Score() {
        return Real_Score;
    }

    public void setReal_Score(Integer real_Score) {
        Real_Score = real_Score;
    }

    public Long getTime_Userd() {
        return Time_Userd;
    }

    public void setTime_Userd(Long time_Userd) {
        Time_Userd = time_Userd;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }
}

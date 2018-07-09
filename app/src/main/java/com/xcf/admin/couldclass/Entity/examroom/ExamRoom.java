package com.xcf.admin.couldclass.Entity.examroom;

import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/12.
 */

public class ExamRoom extends BaseEntity implements Serializable {
    private Long Er_id;
    private String Er_Name;
    private String Er_Start_Date;
    private String Er_End_Date;
    private String Er_Start_Time;
    private String Er_End_Time;
    private Long User_Id;
    private Long UserEx_Id;
    private Long Org_Id;
    private Long Position_Id;
    private Long Major_Id;
    private Long Er_Q_S_Count;
    private Long Er_Q_D_count;
    private Long Er_Q_T_count;
    private Long Er_Q_P_count;
    private Long Er_Type;
    private String Using;
    private Integer TimeUse;

    public Long getEr_id() {
        return Er_id;
    }

    public void setEr_id(Long er_id) {
        Er_id = er_id;
    }

    public String getEr_Name() {
        return Er_Name;
    }

    public void setEr_Name(String er_Name) {
        Er_Name = er_Name;
    }

    public String getEr_Start_Date() {
        return Er_Start_Date;
    }

    public void setEr_Start_Date(String er_Start_Date) {
        Er_Start_Date = er_Start_Date;
    }

    public String getEr_End_Date() {
        return Er_End_Date;
    }

    public void setEr_End_Date(String er_End_Date) {
        Er_End_Date = er_End_Date;
    }

    public String getEr_Start_Time() {
        return Er_Start_Time;
    }

    public void setEr_Start_Time(String er_Start_Time) {
        Er_Start_Time = er_Start_Time;
    }

    public String getEr_End_Time() {
        return Er_End_Time;
    }

    public void setEr_End_Time(String er_End_Time) {
        Er_End_Time = er_End_Time;
    }

    public Long getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(Long user_Id) {
        User_Id = user_Id;
    }

    public Long getUserEx_Id() {
        return UserEx_Id;
    }

    public void setUserEx_Id(Long userEx_Id) {
        UserEx_Id = userEx_Id;
    }

    public Long getOrg_Id() {
        return Org_Id;
    }

    public void setOrg_Id(Long org_Id) {
        Org_Id = org_Id;
    }

    public Long getPosition_Id() {
        return Position_Id;
    }

    public void setPosition_Id(Long position_Id) {
        Position_Id = position_Id;
    }

    public Long getMajor_Id() {
        return Major_Id;
    }

    public void setMajor_Id(Long major_Id) {
        Major_Id = major_Id;
    }

    public Long getEr_Q_S_Count() {
        return Er_Q_S_Count;
    }

    public void setEr_Q_S_Count(Long er_Q_S_Count) {
        Er_Q_S_Count = er_Q_S_Count;
    }

    public Long getEr_Q_D_count() {
        return Er_Q_D_count;
    }

    public void setEr_Q_D_count(Long er_Q_D_count) {
        Er_Q_D_count = er_Q_D_count;
    }

    public Long getEr_Q_T_count() {
        return Er_Q_T_count;
    }

    public void setEr_Q_T_count(Long er_Q_T_count) {
        Er_Q_T_count = er_Q_T_count;
    }

    public Long getEr_Q_P_count() {
        return Er_Q_P_count;
    }

    public void setEr_Q_P_count(Long er_Q_P_count) {
        Er_Q_P_count = er_Q_P_count;
    }

    public Long getEr_Type() {
        return Er_Type;
    }

    public void setEr_Type(Long er_Type) {
        Er_Type = er_Type;
    }

    public String getUsing() {
        return Using;
    }

    public void setUsing(String using) {
        Using = using;
    }

    public Integer getTimeUse() {
        return TimeUse;
    }

    public void setTimeUse(Integer timeUse) {
        TimeUse = timeUse;
    }
}

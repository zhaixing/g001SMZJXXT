package com.xcf.admin.couldclass.Entity.result;


import com.xcf.admin.couldclass.Entity.BaseEntity;
import com.xcf.admin.couldclass.Entity.ques.QuesEntity;

import java.io.Serializable;

public class ResultQues extends BaseEntity implements Serializable {

    private Long Equ_Id;
    private Long Epu_id;
    private String TimeCreated;
    private QuesEntity Ques;
    private Long User_Id;
    private Long UserEx_Id;
    private Long Complete_Id;
    private int Real_Score;
    private String Uesr_Selection;
    private String CompleteTime;

    public Long getEqu_Id() {
        return Equ_Id;
    }

    public void setEqu_Id(Long equ_Id) {
        Equ_Id = equ_Id;
    }

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

    public QuesEntity getQues() {
        return Ques;
    }

    public void setQues(QuesEntity ques) {
        Ques = ques;
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

    public Long getComplete_Id() {
        return Complete_Id;
    }

    public void setComplete_Id(Long complete_Id) {
        Complete_Id = complete_Id;
    }

    public int getReal_Score() {
        return Real_Score;
    }

    public void setReal_Score(int real_Score) {
        Real_Score = real_Score;
    }

    public String getUesr_Selection() {
        return Uesr_Selection;
    }

    public void setUesr_Selection(String uesr_Selection) {
        Uesr_Selection = uesr_Selection;
    }

    public String getCompleteTime() {
        return CompleteTime;
    }

    public void setCompleteTime(String completeTime) {
        CompleteTime = completeTime;
    }
}

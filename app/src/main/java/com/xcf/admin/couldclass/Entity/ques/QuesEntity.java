package com.xcf.admin.couldclass.Entity.ques;


import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class QuesEntity extends BaseEntity implements Serializable {

    private Long Q_Id;


    private String TimeCreated;


    private String Stem;

    private String Q_Key;

    private int Score;


    private Long Position_Id;


    private Long Q_Level;


    private Long Q_Type;


    private String Q_Verify;


    private Long Knowledge_Id;


    private Long User_Id;


    private Long UserEx_Id;


    private Long Major_Id;


    private Long Org_Id;


    private String Using;


    private String Disc;


    private Set<Selection> emp = new HashSet<Selection>();


    public Long getQ_Id() {
        return Q_Id;
    }

    public void setQ_Id(Long q_Id) {
        Q_Id = q_Id;
    }

    public String getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        TimeCreated = timeCreated;
    }

    public String getStem() {
        return Stem;
    }

    public void setStem(String stem) {
        Stem = stem;
    }

    public String getQ_Key() {
        return Q_Key;
    }

    public void setQ_Key(String q_Key) {
        Q_Key = q_Key;
    }

    public Long getQ_Level() {
        return Q_Level;
    }

    public void setQ_Level(Long q_Level) {
        Q_Level = q_Level;
    }

    public Long getQ_Type() {
        return Q_Type;
    }

    public void setQ_Type(Long q_Type) {
        Q_Type = q_Type;
    }

    public String getQ_Verify() {
        return Q_Verify;
    }

    public void setQ_Verify(String q_Verify) {
        Q_Verify = q_Verify;
    }

    public Long getKnowledge_Id() {
        return Knowledge_Id;
    }

    public void setKnowledge_Id(Long knowledge_Id) {
        Knowledge_Id = knowledge_Id;
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

    public Long getMajor_Id() {
        return Major_Id;
    }

    public void setMajor_Id(Long major_Id) {
        Major_Id = major_Id;
    }

    public Long getOrg_Id() {
        return Org_Id;
    }

    public void setOrg_Id(Long org_Id) {
        Org_Id = org_Id;
    }

    public String getUsing() {
        return Using;
    }

    public void setUsing(String using) {
        Using = using;
    }

    public String getDisc() {
        return Disc;
    }

    public void setDisc(String disc) {
        Disc = disc;
    }

    public Set<Selection> getEmp() {
        return emp;
    }

    public void setEmp(Set<Selection> emp) {
        this.emp = emp;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Long getPosition_Id() {
        return Position_Id;
    }

    public void setPosition_Id(Long position_Id) {
        Position_Id = position_Id;
    }
}

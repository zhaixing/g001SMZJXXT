package com.xcf.admin.couldclass.Entity.ques;


import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;

public class Selection extends BaseEntity implements Serializable {


    private Long S_id;


    private String TimeCreated;


    private String S_Key;


    private String S_Name;

    public Long getS_id() {
        return S_id;
    }

    public void setS_id(Long s_id) {
        S_id = s_id;
    }

    public String getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.TimeCreated = timeCreated;
    }

    public String getS_Key() {
        return S_Key;
    }

    public void setS_Key(String s_Key) {
        S_Key = s_Key;
    }

    public String getS_Name() {
        return S_Name;
    }

    public void setS_Name(String s_Name) {
        S_Name = s_Name;
    }
}

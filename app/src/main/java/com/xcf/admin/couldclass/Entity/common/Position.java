package com.xcf.admin.couldclass.Entity.common;

import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;

/**
 * 职位表
 */

public class Position extends BaseEntity implements Serializable {

    private Long Position_Id;


    private String Position_Name;


    private String Major_Id;

    public Long getPosition_Id() {
        return Position_Id;
    }

    public void setPosition_Id(Long position_Id) {
        Position_Id = position_Id;
    }

    public String getPosition_Name() {
        return Position_Name;
    }

    public void setPosition_Name(String position_Name) {
        Position_Name = position_Name;
    }

    public String getMajor_Id() {
        return Major_Id;
    }

    public void setMajor_Id(String major_Id) {
        Major_Id = major_Id;
    }
}

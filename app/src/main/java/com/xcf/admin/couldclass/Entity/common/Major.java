package com.xcf.admin.couldclass.Entity.common;

import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;


public class Major extends BaseEntity implements Serializable {


    private Long Major_Id;


    private String Major_Name;

    public Long getMajor_Id() {
        return Major_Id;
    }

    public void setMajor_Id(Long major_Id) {
        Major_Id = major_Id;
    }

    public String getMajor_Name() {
        return Major_Name;
    }

    public void setMajor_Name(String major_Name) {
        Major_Name = major_Name;
    }
}

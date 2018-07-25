package com.xcf.admin.couldclass.Entity.examroom;

import com.xcf.admin.couldclass.Entity.result.ResultQues;

import java.util.Date;
import java.util.List;

public class Examend {
    private List<ResultQues> list;

    public List<ResultQues> getList() {
        return list;
    }

    public void setList(List<ResultQues> list) {
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
}

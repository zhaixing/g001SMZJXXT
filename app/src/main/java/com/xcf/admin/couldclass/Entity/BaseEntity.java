package com.xcf.admin.couldclass.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/12.
 */

public class BaseEntity implements Serializable {

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

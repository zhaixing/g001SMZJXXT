package com.xcf.admin.couldclass.Entity.login;

import com.xcf.admin.couldclass.Entity.BaseEntity;
import com.xcf.admin.couldclass.Entity.user.User;

import java.io.Serializable;

public class loginuser extends BaseEntity implements Serializable {

    private User user;
    private String bool;
    private String org;
    private String major;
    private String xianzhi;
    private String token;

    public User getUser() {
        return user;
    }

    public String getbool() {
        return bool;
    }

    public String getMajor() {
        return major;
    }

    public String getOrg() {
        return org;
    }

    public String getXianzhi() {
        return xianzhi;
    }

    public String getToken() {
        return token;
    }
}

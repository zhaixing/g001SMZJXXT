package com.xcf.admin.couldclass.Entity.user;


import com.xcf.admin.couldclass.Entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2018-04-02.
 */

public class User extends BaseEntity implements Serializable {

    private Long User_Id;

    private Long User_Code;

    private String User_Pwd;

    private String User_name;

    private String User_Sex;


    private String User_Birthday;


    private String User_Address;


    private String User_Image_Idcard;


    private String User_Image_Head;


    private String Token;


    private java.util.Date TokenOut;

    private String Id_Card;

    private String Phone_Number;

    public String getId_card() {
        return Id_Card;
    }

    public void setId_card(String id_card) {
        Id_Card = id_card;
    }

    public String getPhone_number() {
        return Phone_Number;
    }

    public void setPhone_number(String phone_number) {
        this.Phone_Number = phone_number;
    }

    public Long getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(Long user_Id) {
        User_Id = user_Id;
    }

    public String getUser_Pwd() {
        return User_Pwd;
    }

    public void setUser_Pwd(String user_Pwd) {
        User_Pwd = user_Pwd;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_Sex() {
        return User_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        User_Sex = user_Sex;
    }

    public String getUser_Birthday() {
        return User_Birthday;
    }

    public void setUser_Birthday(String user_Birthday) {
        User_Birthday = user_Birthday;
    }

    public String getUser_Address() {
        return User_Address;
    }

    public void setUser_Address(String user_Address) {
        User_Address = user_Address;
    }

    public String getUser_Image_Idcard() {
        return User_Image_Idcard;
    }

    public void setUser_Image_Idcard(String user_Image_Idcard) {
        User_Image_Idcard = user_Image_Idcard;
    }

    public String getUser_Image_Head() {
        return User_Image_Head;
    }

    public void setUser_Image_Head(String user_Image_Head) {
        User_Image_Head = user_Image_Head;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Date getTokenOut() {
        return TokenOut;
    }

    public void setTokenOut(Date tokenOut) {
        TokenOut = tokenOut;
    }

    public Long getUser_Code() {
        return User_Code;
    }

    public void setUser_Code(Long user_Code) {
        User_Code = user_Code;
    }
}

package com.Introbe.IntuDatabase;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

/*
유저 데이터베이스 기본 추상화 클래스
작성자 이충헌
20200809
*/
public abstract class DataBaseUser {

    private String name;
    private String email;

    //constructor
    public DataBaseUser(FirebaseUser user)
    {
        //null double check 권장
        if(user != null) {
            this.name = user.getDisplayName();
            this.email = user.getEmail();
            // Uri photoUrl = user.getPhotoUrl();// User is signed in
            //User's data
        }
        else
        {

        }
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setEmail(String newEmail)
    {
        this.email = newEmail;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    //return name,email
    public Map<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        return result;
    }






}

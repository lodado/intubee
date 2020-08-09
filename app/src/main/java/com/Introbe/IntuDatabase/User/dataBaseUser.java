package com.Introbe.IntuDatabase.User;

import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

/*
유저 데이터베이스 기본 추상화 클래스
작성자 이충헌
20200809
*/
public abstract class dataBaseUser {

    private String name;
    private String email;
    private Uri myUri;

    //constructor
    public dataBaseUser(FirebaseUser user)
    {
        //null double check 권장
        if(user != null) {
            this.name = user.getDisplayName();
            this.email = user.getEmail();
            this.myUri = user.getPhotoUrl();
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

    public void setUri(Uri newUri) { this.myUri = newUri; }

    public Uri getMyUri()
    {
        return myUri;
    }

    //return name,email
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("Uri", myUri);
        return result;
    }
}

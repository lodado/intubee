package com.Introbe.IntuDatabase.User;


import com.google.firebase.auth.FirebaseUser;

/*
유저 데이터베이스 기본 추상화 클래스
작성자 이충헌
20200809
*/
public abstract class dataBaseUser implements user {

    //private dataBaseManager reader = new dataBaseManager();
    private FirebaseUser ourUser = null;
    //private String name;
    //private String email;
    //private Uri myUri;

    //constructor
    public dataBaseUser(FirebaseUser user)
    {
        //null double check 권장
        if(user != null) {
            ourUser = user;
            //User's data
        }
    }

    public FirebaseUser getOurUser()
    {
        return ourUser;
    }

    //return User's infomations
   /* public Map<String, Object> toMap() {
        return reader.toMap(ourUser);
    }*/
}

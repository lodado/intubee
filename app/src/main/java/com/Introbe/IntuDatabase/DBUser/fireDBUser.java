package com.Introbe.IntuDatabase.DBUser;


import com.google.firebase.auth.FirebaseUser;

/*
유저 데이터베이스 기본 추상화 클래스
작성자 이충헌
20200809
*/
public abstract class fireDBUser implements user {

    //private dataBaseManager reader = new dataBaseManager();
    private FirebaseUser ourUser = null;
    private String name = null;
    //private String email = null;
    //private Uri myUri;

    //constructor
    public fireDBUser(FirebaseUser user)
    {
        //null double check 권장
        if(user != null) {
            ourUser = user;
            name = user.getDisplayName();
            //User's data
        }
    }

    public FirebaseUser getOurUser()
    {
        return ourUser;
    }

    public String getName(){return name;}

    public void setName(String newName){ name = newName; }
    //return User's infomations
   /* public Map<String, Object> toMap() {
        return reader.toMap(ourUser);
    }*/
}

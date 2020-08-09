package com.Introbe.IntuDatabase;


import com.google.firebase.auth.FirebaseUser;

/*
my ID 정보
작성자 이충헌
20200809
*/
public class MyID extends DataBaseUser{

    private static MyID Mine = null;

    //constructor
    private MyID(FirebaseUser user)
    {
        super(user);
    }

    //singleton patton
    //login user must only have a one ID
    public static DataBaseUser getInstance(FirebaseUser user) {

        if(user == null)
        {
            return null;
        }

        if(Mine==null)
        {
            Mine = new MyID(user);
        }

            return Mine;
    }

    //reset
    public static void clearInstance()
    {
        Mine =  null;
    }


}



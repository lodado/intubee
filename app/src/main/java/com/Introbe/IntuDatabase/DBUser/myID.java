package com.Introbe.IntuDatabase.DBUser;


import com.google.firebase.auth.FirebaseUser;

/*
my ID 정보
작성자 이충헌
20200809
*/
public class myID extends fireDBUser {

    private static myID Mine = null;

    //constructor
    private myID(FirebaseUser user)
    {
        super(user);
    }
    //private myID(mysqlUser user){super(user)}

    //singleton patton
    //login user must only have a one ID
    public static myID getInstance(FirebaseUser user) {

        if(user == null)
        {
            return null;
        }

        if(Mine==null)
        {
                Mine = new myID(user);
        }

            return Mine;
    }
    
    //reset
    public static void clearInstance()
    {
        Mine =  null;
    }


}



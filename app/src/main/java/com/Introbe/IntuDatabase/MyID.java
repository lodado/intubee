package com.Introbe.IntuDatabase;

public class MyID extends DataBaseUser{

    private static MyID Mine = null;

    //constructor
    private MyID(String newName, String newEmail)
    {
        super( newName, newEmail);
    }

    //singleton patton
    //login user must only have a one ID
    public static DataBaseUser getInstance(String newName, String newEmail) {

        if(Mine==null)
        {
            Mine = new MyID( newName, newEmail);
        }
            return Mine;
    }
}



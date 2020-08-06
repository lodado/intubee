package com.Introbe.Database;

public class MyID extends DataBaseUser{

    static MyID Mine = null;

    private MyID(String newId, String newName, String newEmail)
    {
        super(newId, newName, newEmail);
    }


    @Override
    public DataBaseUser getInstance(String newId, String newName, String newEmail) {

        if(Mine==null)
        {
            Mine = new MyID(newId, newName, newEmail);
        }
            return Mine;
    }
}



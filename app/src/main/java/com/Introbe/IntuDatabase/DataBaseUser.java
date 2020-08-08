package com.Introbe.IntuDatabase;

import java.util.HashMap;
import java.util.Map;

public abstract class DataBaseUser {

    private String name;
    private String email;

    //constructor
    public DataBaseUser(String newName, String newEmail)
    {
        this.name = newName;
        this.email = newEmail;
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

    //return id,name,email
    public Map<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}

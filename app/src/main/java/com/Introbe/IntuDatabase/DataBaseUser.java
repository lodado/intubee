package com.Introbe.IntuDatabase;

import java.util.HashMap;
import java.util.Map;

public abstract class DataBaseUser {

    public String name;
    public String email;

    //constructor
    public DataBaseUser(String newName, String newEmail)
    {
        this.name = newName;
        this.email = newEmail;
    }

    //return id,name,email
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}

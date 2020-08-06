package com.Introbe.Database;

import java.util.HashMap;
import java.util.Map;

public abstract class DataBaseUser {

    public String id;
    public String name;
    public String email;

    public DataBaseUser()
    {

    }

    public DataBaseUser(String newId, String newName, String newEmail)
    {
        this.id = newId;
        this.name = newName;
        this.email = newEmail;
    }

    //singleton pattern
    public abstract DataBaseUser getInstance(String newId, String newName, String newEmail);

    //return id,name,email
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}

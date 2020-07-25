package com.Introbe.Login;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireDataBase {

    public String id;
    public String name;
    public String email;

    public FireDataBase()
    {} //default constructor

    public FireDataBase(String newId, String newName, String newEmail)
    {
        id = newId;
        name = newName;
        email = newEmail;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("email", email);
        return result;
    }


}

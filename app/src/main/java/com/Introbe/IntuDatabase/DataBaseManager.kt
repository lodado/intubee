package com.Introbe.IntuDatabase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class DataBaseManager {

    //just for record in database
    fun userRecord(user: FirebaseUser?)
    {
        user?.let{
            val database = FirebaseDatabase.getInstance()
            val userRef = database.reference

            user?.apply{
                var usrName = displayName
                var usremail = email

                usremail?.let{
                    userRef.child("users").child(usremail!!).child("email").setValue(usrName)
                    userRef.child("users").child(usremail!!).child("userid").setValue(usremail)
                }

            }
            //just for record in database
        }
    }

}
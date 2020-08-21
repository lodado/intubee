package com.Introbe.IntuDatabase.Util

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class writeFireBase {

    //just for record in database
    fun userWrite(user: FirebaseUser?) {
        user?.let {
            val database = FirebaseDatabase.getInstance()
            val userRef = database.reference

            user?.apply {
                var usrName: String? = displayName
                var usremail: String? = email
                var usruid: String? = uid
                var usrpic: String? = photoUrl.toString()

                usruid?.let {
                    userRef.child("users").child(usruid).child("email").setValue(usremail)
                    userRef.child("users").child(usruid).child("userid").setValue(usrName)
                    userRef.child("users").child(usruid).child("userpic").setValue(usrpic)
                }
            }
            //just for record in database
        }
    }
}
package com.Introbe.IntuDatabase.Util

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.util.*

//파이어베이스 읽을때 쓰는 클래스
class fireDBcontroller {

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

    fun userRead(user: FirebaseUser?): Map<String, Any?>? {
        if(user!=null)
        {

         return toMap(user)
        }
        return null
    }

    //separate it
    fun toMap(user: FirebaseUser): Map<String, Any?>?
    {
        val name = user.displayName
        val email = user.email
        val myUri : Uri? = user.photoUrl

        val result =
            HashMap<String, Any>()
        result["name"] = name!!
        result["email"] = email!!
        if(myUri != null) result["myUri"] = myUri

        return result
    }
}
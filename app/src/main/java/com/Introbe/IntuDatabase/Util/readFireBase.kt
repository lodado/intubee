package com.Introbe.IntuDatabase.Util

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import java.util.*

//파이어베이스 읽을때 쓰는 클래스
class readFireBase {

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
        val name = user?.getDisplayName()
        val email = user.getEmail()
        val myUri : Uri? = user.getPhotoUrl()

        val result =
            HashMap<String, Any>()
        result["name"] = name!!
        result["email"] = email!!
        if(myUri != null) result["myUri"] = myUri

        return result
    }
}
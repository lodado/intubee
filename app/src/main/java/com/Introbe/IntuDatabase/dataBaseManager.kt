package com.Introbe.IntuDatabase

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.Introbe.IntuDatabase.Util.readFireBase
import com.Introbe.IntuDatabase.Util.writeFireBase
import com.Introbe.R
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.firebase.auth.FirebaseUser

class dataBaseManager : AppCompatActivity() ,DBManager{

    private val readingFireBase = readFireBase()
    private val writingFireBase  = writeFireBase()

    override fun <T> read(user: T?): Map<String, Any?>? {

        when(user)
        {
            is FirebaseUser -> return readingFireBase.userRead(user)
        }

        return null
    }

    override fun <T>  write( user: T?) {
        when (user) {
            is FirebaseUser -> writingFireBase.userWrite(user)

        }
    }

    //map 반환
    fun <T> toMap(user: T?): Map<String, Any?>? {
        when(user)
        {
            is FirebaseUser -> return readingFireBase.toMap(user)
        }

        return null
    }

}
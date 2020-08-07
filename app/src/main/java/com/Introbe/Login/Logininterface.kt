package com.Introbe.Login

import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.loginpopup.*

interface Logininterface {

    open fun SuccessLogin(user : FirebaseUser?)
    {

    }

    fun toastText(txt : EditText, func : Toast) : Boolean
    {
        var newtxt = txt.getText().toString().trim()

        if(newtxt.length<=0)
        {
            func.show()
            return false
        }

        return true
    }
}
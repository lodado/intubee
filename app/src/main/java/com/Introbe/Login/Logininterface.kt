package com.Introbe.Login

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseUser

interface Logininterface {

    open fun SuccessLogin(user : FirebaseUser?)
    {

    }

}
package com.Introbe.IntuDatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Introbe.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.user_page.*
import java.lang.NullPointerException

class UserPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_page)

        val mine: Map<String, String>? = FirebaseAuth.getInstance().currentUser?.run {
            MyID.getInstance(displayName, email).toMap()
        }

        if (mine == null)
        {
            Toast.makeText(this,
                "아이디가 없습니다 오류!",
                Toast.LENGTH_LONG
            ).show()
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        userpageID1.text = mine?.get("name")  //setID
        userpageEmail.text = mine?.get("email") //SetEmail


    }
}
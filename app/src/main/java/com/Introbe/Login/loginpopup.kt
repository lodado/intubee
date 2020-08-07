package com.Introbe.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.Introbe.ExampleActivity
import com.Introbe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.loginpopup.*
import java.lang.Exception

class loginpopup : AppCompatActivity(), Logininterface {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpopup)

        auth = FirebaseAuth.getInstance()
        editenterbtn.setOnClickListener {
            signUp()
        }

    }



    fun signUp() {

        val getMap  : Map<EditText, String> =
            mapOf(editid to "ID", editemail to "Email", editpass to "Password", editrepass to "re-Password")

        for ((key, value) in getMap ){

            val newstr : String =  value+"를 입력하세요"

            if(!toastText(key,Toast.makeText(
                    this,
                    newstr,
                    newstr.length
                ))
            )
                return
        }

        if (!editpass.text?.toString().equals(editrepass.text.toString())) {

            val newtext: String = "비밀번호가 틀립니다"
            Toast.makeText(
                this,
                newtext,
                newtext.length
            ).show()

            return
        }

        auth?.createUserWithEmailAndPassword(
            editemail.text.toString(),
            editpass.text.toString()
        )?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                SuccessLogin(task.result?.user)
                //creating user
            } else if (!task.exception?.message.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    task.exception?.message,
                    Toast.LENGTH_LONG
                ).show()
                //show Error
            } else {
                finish()
            }
        }

    }


    override fun SuccessLogin(user: FirebaseUser?) {
        user?.let {

            //startActivity(Intent(this, ExampleActivity::class.java))
            //호출 another
            finish()
        }
    }

}
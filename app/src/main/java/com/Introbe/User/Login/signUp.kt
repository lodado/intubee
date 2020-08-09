package com.Introbe.User.Login

import android.os.Bundle
import android.widget.Toast
import com.Introbe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.loginpopup.*

/*
회원가입 화면
작성자 이충헌
20200809
*/
class signUp : loginInterFace() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpopup)

        auth = FirebaseAuth.getInstance()
        editenterbtn.setOnClickListener {
            signUp()
        }

    }

    fun signUp() {

        val myid: String? = toastText(editid)
        val myemail: String? = toastText(editemail)
        val pass1: String? = toastText(editpass)
        val pass2: String? = toastText(editrepass)

        //check null
        myid?.let{} ?: return
        myemail?.let {} ?: return
        pass1?.let {} ?: return
        pass2?.let {} ?: return

        if (pass1 != pass2) {

            val newtext: String = "비밀번호가 틀립니다"
            Toast.makeText(
                this,
                newtext,
                newtext.length
            ).show()

            return
        }

        auth?.createUserWithEmailAndPassword(
            myemail,
            pass1
        )?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                successActive(task.result?.user)
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

    @Override
    override fun successActive(user: FirebaseUser?) {

        user?.let {
            //아이디 업데이트
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(editid.text.toString())
                .build()

            user.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    }
                }

            Toast.makeText(
                this,
                "아이디가 성공적으로 생성 되었습니다.",
                Toast.LENGTH_LONG
            ).show()

            finish()
        }
    }


}


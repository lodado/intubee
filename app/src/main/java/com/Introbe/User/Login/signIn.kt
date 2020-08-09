package com.Introbe.User.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.Introbe.IntuDatabase.dataBaseManager
import com.Introbe.R
import com.Introbe.mainpage.mainActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

/*
로그인 화면
작성자 이충헌
20200809
*/
class signIn : loginInterFace() {

    var auth: FirebaseAuth? = null
    var googleSignInID : GoogleSignInClient?= null

    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("280608505320-4124lqevvm59msasm6k7r7o8itftjp4t.apps.googleusercontent.com")
        .requestEmail()
        .build()

    var GOOGLE_LOGIN_CODE = 9001
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        btnlogin.setOnClickListener {
            signInEmail()
        }

        googleSignInID = GoogleSignIn.getClient(this, gso)

        btnmakeID.setOnClickListener{
            startActivity(Intent(this, signUp::class.java))
            //회원가입
        }

        forgetpasswd.setOnClickListener{
            Toast.makeText(this,
                "Email me ycp998@naver.com",
                Toast.LENGTH_LONG
            ).show()
        }


        googleloginbtn.setOnClickListener{
           googleLogin()
            //구글 로그인
        }
    }

    fun googleLogin() {
        var signInIntent = googleSignInID?.signInIntent

        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode == GOOGLE_LOGIN_CODE)
        {


            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            result?.let {
                //test
                if(result.isSuccess) {
                    var account = result.signInAccount
                    //second

                    firebaseAuthWithGoogle(account!!)

                }
            }
        }
    }

    fun firebaseAuthWithGoogle(account : GoogleSignInAccount)
    {
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                task->
            if(task.isSuccessful)
            {
                successActive(task.result?.user)//creating user
            }
            else {
                Toast.makeText(this,
                    task.exception?.message,
                    Toast.LENGTH_LONG
                ).show()
                //show Error
            }
        }
    }



    fun signInEmail()
    {

        val id  : String? = toastText(idbar)
        val pass: String? = toastText(passwordbar)

        id?.let{}   ?: return
        pass?.let{} ?: return
        //널체크

        auth?.signInWithEmailAndPassword(
            id,
            pass
        )?.addOnCompleteListener {
                task->

            if(task.isSuccessful)
            {
                successActive(task.result?.user)//creating user
            }
            else {
                Toast.makeText(this,
                    task.exception?.message,
                    Toast.LENGTH_LONG
                ).show()
                //show Error
            }
        }
    }

    @Override
    override fun successActive(user : FirebaseUser?)
    {
        user?.let{
            dataBaseManager().write("users",user)

            startActivity(Intent(this, mainActivity::class.java))
                //호출 another
            }
        }
    }



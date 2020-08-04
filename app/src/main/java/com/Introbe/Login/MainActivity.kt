package com.Introbe.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.Introbe.ExampleActivity
import com.Introbe.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Logininterface {

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
            signinEmail()
        }

        googleSignInID = GoogleSignIn.getClient(this, gso)

        btnmakeID.setOnClickListener{
            startActivity(Intent(this, loginpopup::class.java))
            //회원가입
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



            if (result != null) {
                if(result.isSuccess) {
                    var account = result.signInAccount
                    //second

                    firebaseAuthWithGoogle(account!!)

                }

            }
            else
            {

            }
        }
        else
        {

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
                SuccessLogin(task.result?.user)//creating user
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





    fun signinEmail()
    {
        auth?.signInWithEmailAndPassword(idbar.text.toString(),
            passwordbar.text.toString()
        )?.addOnCompleteListener {
                task->
            if(task.isSuccessful)
            {
                SuccessLogin(task.result?.user)//creating user
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

    override fun SuccessLogin(user : FirebaseUser?)
    {
        user?.let{

            startActivity(Intent(this, ExampleActivity::class.java))
            //호출 another
        }
    }



}
package com.Introbe.User

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Introbe.IntuDatabase.User.myID
import com.Introbe.R
import com.Introbe.User.Login.signIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.user_page.*


/*
my page
작성자 이충헌
20200809
*/
class userPage : AppCompatActivity() {

    var photoUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_page)

        val mine: Map<String, Any>? = FirebaseAuth.getInstance().currentUser?.run {
            myID.getInstance(this).toMap()
        }

        mine?.let{
            userpageID1.text = mine?.get("name").toString()  //setID
            userpageEmail.text = mine?.get("email").toString() //SetEmail

            photoUri = mine?.get("myUri") as Uri?

            photoUri?.let{
                val imageView1 : ImageView = findViewById(R.id.userpageImage)

               Glide.with(this)
                //imageView1.setImageURI(photoUri)

            }


        }  ?: let()
            {
                Toast.makeText(
                    this,
                    "아이디가 없습니다 오류!",
                    Toast.LENGTH_LONG
                ).show()

                android.os.Process.killProcess(android.os.Process.myPid());
            }

        btnlogout.setOnClickListener{

            //logout
            FirebaseAuth.getInstance().signOut();

            //clear singleton
            myID.clearInstance()

            Toast.makeText(this,
                "log out",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this, signIn::class.java))
        }
    }
}
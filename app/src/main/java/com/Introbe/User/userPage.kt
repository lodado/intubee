package com.Introbe.User

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.Introbe.IntuDatabase.DBManager
import com.Introbe.IntuDatabase.User.myID
import com.Introbe.IntuDatabase.dataBaseManager
import com.Introbe.R
import com.Introbe.User.Login.signIn
import com.Introbe.Utill.photo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.user_page.*


/*
my page
작성자 이충헌
20200809
*/
class userPage : photo() {

    private val DBs : DBManager = dataBaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_page)

        //update firebaseUserProfile
        googleUserProfile()

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

    //update firebaseUserProfile
    private final fun googleUserProfile() {

            FirebaseAuth.getInstance().currentUser?.let {

                DBs.read(myID.getInstance(it).ourUser)?.apply {

                    userpageID1.text = get("name").toString()  //setID
                    userpageEmail.text = get("email").toString() //SetEmail
                    circleImage(R.id.userpageImage,this@userPage, get("myUri") as Uri?) //setProfilePicture
                }
            }
        }
}
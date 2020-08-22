package com.Introbe.User

import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.Introbe.IntuDatabase.DBManager
import com.Introbe.IntuDatabase.User.myID
import com.Introbe.IntuDatabase.Util.photo
import com.Introbe.IntuDatabase.dataBaseManager
import com.Introbe.R
import com.Introbe.User.Login.signIn
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
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
        fireBaseProfile()

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
    private final fun fireBaseProfile() {

            FirebaseAuth.getInstance().currentUser?.let {

                DBs.read(myID.getInstance(it).ourUser)?.apply {

                    userpageID1.text = get("name").toString()  //setID
                    userpageEmail.text = get("email").toString() //SetEmail

                    val imageView : ImageView

                    val photoUri : Uri? = get("myUri") as Uri?;

                    if(photoUri != null) {
                        imageView = findViewById(R.id.userpageImage)

                        imageView.setBackground(ShapeDrawable(OvalShape()))
                        if (Build.VERSION.SDK_INT >= 21) {
                            imageView.setClipToOutline(true);
                        }
                        Glide.with(this@userPage).load(photoUri)
                            .signature(ObjectKey(System.currentTimeMillis()))
                            .into(imageView)
                        //imageView.setImageURI(photoUri)
                    }
                }
            }
        }
}
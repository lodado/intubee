package com.Introbe.IntuDatabase.Util

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


open class photo : AppCompatActivity() {

    protected fun getURI(str :String): StorageReference {

        var storage: FirebaseStorage = FirebaseStorage.getInstance();
        var storageRef: StorageReference = storage.getReference()

        return Firebase.storage.reference
    }

    //동그란 사진 업데이트
    /*
    *  rPage R.id.사진
    *  myClass this@class명
    *  phtoUri URL
     */
    protected fun circleImage(rPage: Int, myclass: AppCompatActivity, str: String) {
        val photoUri: StorageReference = getURI(str)

        if (photoUri != null) {
            //프로필 이미지 load
            val imageView1: ImageView
            try {
                imageView1 = findViewById(rPage)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    e.stackTrace.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                return;
            }

            imageView1.setBackground(ShapeDrawable(OvalShape()))
            if (Build.VERSION.SDK_INT >= 21) {
                imageView1.setClipToOutline(true);
            }
            pictureUpdatebyGlide(myclass, imageView1, photoUri)
        } else {
            //error
        }
    }

    //사각형 사진 업데이트
    /*
   *  rPage R.id.사진
   *  myClass this@class명
   *  phtoUri URL
    */
    protected open fun squareImage(rPage: Int, myclass: AppCompatActivity, str: String) {
        val photoUri: StorageReference = getURI(str)

        val imageView1: ImageView
        try {
            imageView1 = findViewById(rPage)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                e.stackTrace.toString(),
                Toast.LENGTH_SHORT
            ).show()

            return;
        }

        pictureUpdatebyGlide(myclass, imageView1, photoUri)
    }

    //Glide를 이용하여 업데이트
    /*
   *  rPage R.id.사진
   *  myClass this@class명
   *  phtoUri URL
    */
    protected open fun pictureUpdatebyGlide(
        myclass: AppCompatActivity,
        imgView: ImageView,
        photoUri: StorageReference
    ) {
        if (photoUri != null) {
            Glide.with(myclass).load(photoUri)
                .signature(ObjectKey(System.currentTimeMillis()))
                .into(imgView)
            //imgView.setImageURI(photoUri)
        }
    }

}
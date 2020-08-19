package com.Introbe.Utill

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.Introbe.R
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey

open class photo : AppCompatActivity() {

    //동그란 사진 업데이트
    /*
    *  rPage R.id.사진
    *  myClass this@class명
    *  phtoUri URL
     */
    protected fun circleImage(rPage: Int, myclass: AppCompatActivity, photoUri: Uri?)
    {
        if(photoUri != null)
        {
            //프로필 이미지 load
            val imageView1: ImageView = findViewById(rPage)
            imageView1.setBackground(ShapeDrawable(OvalShape()))
            if (Build.VERSION.SDK_INT >= 21) {
                imageView1.setClipToOutline(true);
            }
            pictureUpdatebyGlide(myclass, imageView1, photoUri)
        }
        else
        {
            //error
        }
    }

    //사각형 사진 업데이트
    /*
   *  rPage R.id.사진
   *  myClass this@class명
   *  phtoUri URL
    */
    protected open fun squareImage(rPage: Int, myclass: AppCompatActivity, photoUri: Uri?)
    {
        val imageView1: ImageView = findViewById(rPage)
        pictureUpdatebyGlide(myclass, imageView1, photoUri)
    }

    //Glide를 이용하여 업데이트
    /*
   *  rPage R.id.사진
   *  myClass this@class명
   *  phtoUri URL
    */
    protected open fun pictureUpdatebyGlide(myclass:AppCompatActivity, imgView : ImageView, photoUri : Uri?)
    {
        if(photoUri != null)
        {
            Glide.with(myclass).load(photoUri.toString())
                .signature(ObjectKey(System.currentTimeMillis()))
                .into(imgView)
            imgView.setImageURI(photoUri)
        }
    }

}
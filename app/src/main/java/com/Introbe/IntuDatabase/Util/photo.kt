package com.Introbe.IntuDatabase.Util

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Introbe.mainpage.Board.contentDTO
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.*


open class photo : AppCompatActivity() {

    protected var auth : FirebaseAuth? = null
    protected var firestore : FirebaseFirestore? = null
    private var contentDTOS : ArrayList<contentDTO>? = null

    open fun getContentDTO(): ArrayList<contentDTO>? {

        wtffucntion()

        print("wtf")
        return contentDTOS
    }
    //매우 느림
    private fun wtffucntion() {
        contentDTOS = arrayListOf()

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("images")?.orderBy("timestamp")
            ?.addSnapshotListener label@{ querySnapshot, firebaseFirestoreExec ->

                contentDTOS!!.clear()
                try{
                    for (snapshot in querySnapshot!!.documents) {

                        var item = snapshot.toObject(contentDTO::class.java)
                        contentDTOS!!.add(item!!)
                    }
                }
               catch(e : Exception)
               {
                   finish()
               }

                println(contentDTOS!!.size)
                updatingList(contentDTOS!!)
            }
    }

    private fun updatingList(lis : ArrayList<contentDTO>)
    {
        contentDTOS = lis
    }



    protected fun getReference(str :String): StorageReference {

        var storage: FirebaseStorage = FirebaseStorage.getInstance();
        var storageRef: StorageReference = storage.reference

        return Firebase.storage.reference
    }

    //동그란 사진 업데이트
    /*
    *  rPage R.id.사진
    *  myClass this@class명
    *  phtoUri URL
     */
    fun circleImage(rPage: Int, myclass: AppCompatActivity, photoUri: Uri?) {

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
            imageView1.setScaleType(ImageView.ScaleType.FIT_XY)
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
    open fun squareImage(rPage: Int, myclass: AppCompatActivity, photoUri: Uri?) {

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

       imageView1.setScaleType(ImageView.ScaleType.FIT_XY)

        pictureUpdatebyGlide(myclass, imageView1, photoUri)
    }

    //Glide를 이용하여 업데이트
    /*
   *  rPage R.id.사진
   *  myClass this@class명
   *  phtoUri URL
    */
    open fun pictureUpdatebyGlide(
        myclass: AppCompatActivity,
        imgView: ImageView,
        photoUri: Uri?
    ) {

        if (photoUri != null) {
            Glide.with(myclass).load(photoUri)
                .signature(ObjectKey(System.currentTimeMillis()))
                .into(imgView)
            //imgView.setImageURI(photoUri)
        }
    }

}
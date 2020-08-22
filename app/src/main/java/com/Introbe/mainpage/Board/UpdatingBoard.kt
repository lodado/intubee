package com.Introbe.mainpage.Board

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.Introbe.IntuDatabase.Util.photo
import com.Introbe.IntuDatabase.Util.photoUpload
import com.Introbe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.photoup.*
import java.text.SimpleDateFormat
import java.util.*

//게시판에 자신의 게시판 업데이트
class UpdatingBoard : AppCompatActivity() {

    var PICK_IMAGE_FROM_ALBUM = 0

    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null

    //var auth : FirebaseAuth? = null
    //var firestore : FirebaseFirestore? = null

    var photoManager = photoUpload()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.photoup)

            //init
            storage = FirebaseStorage.getInstance()

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1).run{
        photouploadimg.setOnClickListener {

                var photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"

                startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
            }
        }


            photoupcancel.setOnClickListener{
                finish()
            }

            photoupaccept.setOnClickListener{
                contentUpload()
                finish()
            }


    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if(requestCode == PICK_IMAGE_FROM_ALBUM)
            {
                photoUri = data?.data
                photouploadimg.setImageURI(photoUri)

            }
            else
            {
                finish()
                //exit
            }
        }

        fun contentUpload() {

           photoManager.photoUpload(storage,
                photoUri,
                Toast.makeText(
                this,
                "업로드 ",
                Toast.LENGTH_SHORT)
           ,editphotoex
            )

        }




        

    }

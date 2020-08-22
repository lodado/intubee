package com.Introbe.IntuDatabase.Util

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.Introbe.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.photoup.*
import java.text.SimpleDateFormat
import java.util.*

class photoUp : AppCompatActivity() {

    var PICK_IMAGE_FROM_ALBUM = 0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photoup)

        storage = FirebaseStorage.getInstance()
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1).run{
        photouploadimg.setOnClickListener {


                var photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"

                startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
            }
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

            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            val imageFileName = "IMAGE_"+timestamp+"_.png"

            var storageRef = storage?.reference?.child("images")?.child(imageFileName)

            storageRef?.putFile(photoUri!!)?.addOnSuccessListener {

                Toast.makeText(
                    this,
                   "업로드 ",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }




        

    }

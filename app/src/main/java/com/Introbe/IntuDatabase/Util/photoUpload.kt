package com.Introbe.IntuDatabase.Util

import android.app.Activity
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import com.Introbe.mainpage.Board.contentDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.photoup.*
import java.text.SimpleDateFormat
import java.util.*

class photoUpload : photo() {

    open fun photoUpload(storage : FirebaseStorage?,
                         photoUri : Uri?,
                         tst :Toast,
                         imgtext : EditText,
                         new_timestamp: String
                         = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())){
        val imageFileName = "IMAGE_"+new_timestamp+"_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener { uri ->
            tst.show()


            var auth : FirebaseAuth? = null
            var firestore : FirebaseFirestore? = null

                       auth = FirebaseAuth.getInstance()
                       firestore = FirebaseFirestore.getInstance()

                       var contentDTO = contentDTO()


                       with(contentDTO)
                       {

                           imageUrl = uri.toString()

                           uid = auth?.currentUser?.uid

                           userId = auth?.currentUser?.email

                           explain = imgtext?.text.toString()

                           timestamp = System.currentTimeMillis()

                       }

                       firestore?.collection("images")?.document(contentDTO.timestamp.toString())?.set(contentDTO)
                       setResult(Activity.RESULT_OK)

        }
    }



}
package com.Introbe.IntuDatabase.Util

import android.net.Uri
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class photoUpload : photo() {

    open fun photoUpload(storage : FirebaseStorage?,
                         photoUri : Uri?,
                         tst :Toast,
                         timestamp: String
                         = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())){
        val imageFileName = "IMAGE_"+timestamp+"_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
            tst.show()

            var auth : FirebaseAuth? = null
            var firestore : FirebaseFirestore? = null

            auth = FirebaseAuth.getInstance()
            firestore = FirebaseFirestore.getInstance()
        }
    }

    

}
package com.Introbe.IntuDatabase.Util

import android.app.Activity
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import com.Introbe.IntuDatabase.DBUser.myID
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
                         img : String = "IMAGE",
                         new_timestamp: String
                         = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())){
        val imageFileName = img+"_"+new_timestamp+"_.png"
        var storageRef = storage?.reference?.child(img)?.child(imageFileName)

        storageRef?.putFile(photoUri!!)?.addOnSuccessListener { _ ->
            tst.show()

                       auth = FirebaseAuth.getInstance()
                       firestore = FirebaseFirestore.getInstance()

                       var contentDTO = contentDTO()

                      contentDTO.run{

                          imageUrl = imageFileName
                          explain = imgtext?.text.toString()
                          timestamp = System.currentTimeMillis()

                          auth?.currentUser?.also{
                              this.uid = it.uid
                              this.userEmail= it.email
                              this.userId = it.displayName
                           }

                           this
                       }

                       firestore?.collection("images")?.document()?.set(contentDTO)
                       setResult(Activity.RESULT_OK)
        }
    }
}
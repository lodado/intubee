package com.Introbe.Login

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.Introbe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.loginpopup.*


class loginpopup : AppCompatActivity(), Logininterface {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpopup)

        auth = FirebaseAuth.getInstance()
        editenterbtn.setOnClickListener {
            signUp()
        }

    }

    fun signUp() {

        val getMap: Map<EditText, String> =
            mapOf(
                editid to "ID",
                editemail to "Email",
                editpass to "Password",
                editrepass to "re-Password"
            )

        for ((key, value) in getMap) {

            val newstr: String = value + "를 입력하세요"

            if (!toastText(
                    key, Toast.makeText(
                        this,
                        newstr,
                        newstr.length
                    )
                )
            )
                return
        }

        if (!editpass.text?.toString().equals(editrepass.text.toString())) {

            val newtext: String = "비밀번호가 틀립니다"
            Toast.makeText(
                this,
                newtext,
                newtext.length
            ).show()

            return
        }

        auth?.createUserWithEmailAndPassword(
            editemail.text.toString(),
            editpass.text.toString()
        )?.addOnCompleteListener { task ->
            if (task.isSuccessful) {


                SuccessLogin(task.result?.user)
                //creating user
            } else if (!task.exception?.message.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    task.exception?.message,
                    Toast.LENGTH_LONG
                ).show()
                //show Error
            } else {
                finish()
            }
        }


    }


    override fun SuccessLogin(user: FirebaseUser?) {
        user?.let {

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(editid.text.toString())
                .build()

            user.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    }
                }

            Toast.makeText(
                this,
                "아이디가 성공적으로 생성 되었습니다.",
                Toast.LENGTH_LONG
            ).show()

            //val database = FirebaseDatabase.getInstance()
            //val userRef = database.getReference()

            //userRef.child("users").child("email").setValue(editemail.text.toString())
            //userRef.child("users").child("email").setValue(editid.text.toString())

            finish()
            }
        }


    }


package com.Introbe.User.Login

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loginpopup.*



/*
로그인 기본 추상화 클래스
작성자 이충헌
20200809
*/
abstract class loginInterFace : AppCompatActivity() {

    //작동 실행 성공시 쓰는 function
    open fun successActive(user: FirebaseUser?) {

    }

    //edittext를 받아서 널체크하고 널이면 유저에게 알려주고, 널 아니면 string 반환
    protected fun toastText(txt: EditText): String? {
        val getMap: Map<EditText, String> =
            mapOf(
                editid to "ID",
                editemail to "Email",
                editpass to "Password",
                editrepass to "re-Password",
                idbar to "ID",
                passwordbar to "Password"
            )

        var newtxt: String

        for ((key, value) in getMap) {
            if (key == txt) {
                newtxt = txt.text.toString().trim()

                if (newtxt.length <= 0){

                    Toast.makeText(
                        this,
                        value+"를 입력하세요",
                        Toast.LENGTH_SHORT
                    ).show()

                    return null //null
                }
                return newtxt
            }
        }
        return null //programming error
    }
}
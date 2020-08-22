package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.fireDBcontroller
import com.google.firebase.auth.FirebaseUser

class dataBaseManager : DBManager{

    private val FireBasecontroller = fireDBcontroller()

    override fun <T> read(user: T?): Map<String, Any?>? {

        when(user)
        {
            is FirebaseUser -> return FireBasecontroller.userRead(user)
            //is mysqlUser
        }

        return null
    }

    override fun <T>  write( user: T?) {
        when (user) {
            is FirebaseUser -> FireBasecontroller.userWrite(user)
            //is mysqlUser
        }
    }

    //map 반환
    fun <T> toMap(user: T?): Map<String, Any?>? {
        when(user)
        {
            is FirebaseUser -> return FireBasecontroller.toMap(user)
            //is mysqlUser
        }

        return null
    }

}
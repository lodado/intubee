package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.readFireBase
import com.Introbe.IntuDatabase.Util.writeFireBase
import com.google.firebase.auth.FirebaseUser

class dataBaseManager : DBManager{

    private val readingFireBase = readFireBase()
    private val writingFireBase  = writeFireBase()

    override fun <T> read(user: T?): Map<String, Any?>? {

        when(user)
        {
            is FirebaseUser -> return readingFireBase.userRead(user)
            //is mysqlUser
        }

        return null
    }

    override fun <T>  write( user: T?) {
        when (user) {
            is FirebaseUser -> writingFireBase.userWrite(user)
            //is mysqlUser
        }
    }

    //map 반환
    fun <T> toMap(user: T?): Map<String, Any?>? {
        when(user)
        {
            is FirebaseUser -> return readingFireBase.toMap(user)
            //is mysqlUser
        }

        return null
    }

}
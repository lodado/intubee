package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.readFireBase
import com.Introbe.IntuDatabase.Util.writeFireBase
import com.google.firebase.auth.FirebaseUser

class dataBaseManager : DBManager{

    override val reading = readFireBase()
    override val writing = writeFireBase()

    override fun <T> read(user: T?)
    {

    }

    override fun <T>  write( user: T?)
    {
       when(user)
       {
          is FirebaseUser ->  writing.userWrite(user)

       }

    }



}
package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.readFireBase
import com.Introbe.IntuDatabase.Util.writeFireBase
import com.google.firebase.auth.FirebaseUser

class dataBaseManager : DBManager <FirebaseUser>{

    override val reading = readFireBase()
    override val writing = writeFireBase()

    override fun read(kindof : String, user: FirebaseUser?)
    {

    }

    override fun write(kindof : String, user: FirebaseUser?)
    {
        if(kindof == "users")
        {
            writing.userWrite(user)
        }

    }



}
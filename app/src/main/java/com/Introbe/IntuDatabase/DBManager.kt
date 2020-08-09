package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.*

public interface DBManager <in T>{

    val reading : readFireBase
    val writing : writeFireBase

    open fun write(kindof : String, user : T?)
    {

    }

    open fun read(kindof : String, user: T?)
    {

    }
}
package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.*

public interface DBManager{

    val reading : Any
    val writing : Any

    open fun <T> write(user : T?)
    {

    }

    open fun <T> read(user : T?)
    {

    }
}
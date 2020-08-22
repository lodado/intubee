package com.Introbe.IntuDatabase

import com.Introbe.IntuDatabase.Util.*

public interface DBManager{
    open fun <T> write(user : T?)

    open fun <T> read(user : T?) : Map<String, Any?>?

    //its useless for now
}
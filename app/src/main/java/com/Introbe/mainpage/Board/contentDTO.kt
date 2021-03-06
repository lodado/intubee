package com.Introbe.mainpage.Board

data class contentDTO(
    var explain: String? = null,
    var imageUrl: String? = null,
    var uid: String? = null,
    var userId: String? = null,
    var timestamp: Long? = null,
    var favoriteCount: Int = 0,
    var userEmail:String?= null,
    var favorites: Map<String, Boolean> = HashMap()) {
    data class Comment(var uid : String? = null,
                       var userId : String? = null,
                       var comment: String? = null,
                       var timestamp :Long? = null
                       )
}


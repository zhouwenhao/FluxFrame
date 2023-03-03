package com.timark.sundy.http

class HttpResp<T> {
    var code : String? = null
    var msg : String? = null
    var data : T? = null
    var success : Boolean = false

    fun isSuc() : Boolean{
        return success
    }
}
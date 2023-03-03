package com.timark.sundy.http

import com.timark.net.NetObser

abstract class HttpObser<T>(tag : String) : NetObser<HttpResp<T>>(tag) {

    override fun isSuc(value: HttpResp<T>): Boolean {
        return value.isSuc()
    }

    override fun respByException(e: Throwable?): HttpResp<T> {
        return HttpResp<T>().apply {
            code = "999999"
            msg = "error"
            success = false
        }
    }
}
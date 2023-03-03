package com.timark.sundy.http

import com.timark.net.NetInterceptor
import okhttp3.Request
import okio.IOException

class HttpInterceptor : NetInterceptor() {
    override fun generateHeader(builder: Request.Builder) {

    }

    override fun log(
        method: String,
        url: String,
        mediaType: String?,
        headers: String?,
        requestBody: String?,
        responseBody: String?,
        resHttpCode: Int?,
        throwable: IOException?,
        usedTime: Long
    ) {

    }
}
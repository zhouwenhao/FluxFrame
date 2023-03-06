package com.timark.sundy.http

import android.util.Log
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
        val sb = StringBuilder()
        sb.append("${method}  ${url}").append("\n")
        sb.append("mediaType=${mediaType}").append("\n")
        sb.append("headers=${headers}").append("\n")
        sb.append("request=${requestBody}").append("\n")
        sb.append("response=${responseBody}").append("\n")
        sb.append("httpCode=${resHttpCode}").append("\n")
        sb.append("throwable=${throwable}").append("\n")
        sb.append("usedTime=${usedTime}").append("\n")
        Log.d("HttpInterceptor", sb.toString())
    }
}
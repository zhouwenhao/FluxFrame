package com.timark.net

import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.IOException
import java.nio.charset.Charset

abstract class NetInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val oriRequest = chain.request()
        val httpUrl = oriRequest.url

        val builder = oriRequest.newBuilder()
        builder.url(httpUrl)
        generateHeader(builder)

        val newRequest = builder.build()
        val startTime = System.currentTimeMillis()
        var catchException : IOException? = null
        var response : Response? = null
        var resHttpCode : Int? = null

        try {
            response = chain.proceed(newRequest)
            resHttpCode = response.code
        } catch (e : IOException){
            catchException = e
        }

        val usedTime = System.currentTimeMillis() - startTime

        var oriResStr : String? = null
        if (catchException == null){
            if (response!!.body != null){
                oriResStr = response.body!!.string()
            } else {
                catchException = IOException("body is empty")
            }
        }

        log(newRequest.method, newRequest.url.toString(), readMediaType(newRequest.body), newRequest.headers.toString(), readRequestBody(newRequest.body), oriResStr, resHttpCode, catchException, usedTime)

        if (catchException != null){
            throw catchException
        } else {
            return response!!.newBuilder().code(resHttpCode!!).body(oriResStr!!.toResponseBody(response.body!!.contentType())).build()
        }
    }

    private fun readRequestBody(requestBody : RequestBody?) : String?{
        requestBody?.let {
            try {
                val buffer = Buffer()
                it.writeTo(buffer)
                var charset = Charset.forName("UTF-8")
                it.contentType()?.let { rt ->
                    charset = rt.charset(charset)
                }
                return buffer.readString(charset)
            } catch (e : Exception){

            }
        }
        return null
    }

    private fun readMediaType(requestBody: RequestBody?) : String?{
        requestBody?.let {
            return it.contentType().toString()
        }
        return null
    }

    abstract fun generateHeader(builder : Request.Builder)

    abstract fun log(method : String, url : String, mediaType : String?, headers : String?, requestBody : String?, responseBody : String?, resHttpCode : Int?, throwable : IOException?, usedTime : Long)
}
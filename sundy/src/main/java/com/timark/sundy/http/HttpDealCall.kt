package com.timark.sundy.http

interface HttpDealCall {
    fun deal(error : HttpResp<Any>)
}
package com.example.flux_demo

import com.timark.business.BusiApplication
import com.timark.sundy.http.HttpDealCall
import com.timark.sundy.http.HttpDealMgr
import com.timark.sundy.http.HttpResp

class MainApplication : BusiApplication() {

    override fun onCreate() {
        super.onCreate()

        HttpDealMgr.registCall(object : HttpDealCall{
            override fun deal(error: HttpResp<Any>) {
                //可以进行toast
                //可以对code进行额外处理
            }
        })
    }
}
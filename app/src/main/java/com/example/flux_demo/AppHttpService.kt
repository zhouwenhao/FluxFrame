package com.example.flux_demo

import com.timark.net.NetDomain
import com.timark.net.NetTimeOut
import com.timark.sundy.http.HttpInterceptor

@NetDomain(domain = "http://api.timark.com/", moduleId = BuildConfig.APPLICATION_ID, interceptorClazz = HttpInterceptor::class)
@NetTimeOut(connectTimeOutSec = 40L, readTimeOutSec = 40L, writeTimeOutSec = 40L)
interface AppHttpService {
}
package com.timark.net

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NetTimeOut(val connectTimeOutSec : Long, val readTimeOutSec : Long, val writeTimeOutSec : Long)

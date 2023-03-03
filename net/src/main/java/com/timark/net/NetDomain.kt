package com.timark.net

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NetDomain(val domain : String, val moduleId : String, val interceptorClazz : KClass<out NetInterceptor>)

package com.timark.flux

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FluxObser(val action : String, val curThread : Boolean = true)

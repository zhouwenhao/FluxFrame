package com.timark.business.net

import com.timark.business.BuildConfig
import com.timark.business.BusiConf
import com.timark.business.model.SplashItem
import com.timark.net.NetDomain
import com.timark.sundy.http.HttpInterceptor
import com.timark.sundy.http.HttpResp
import io.reactivex.Observable
import retrofit2.http.Headers
import retrofit2.http.POST

@NetDomain(domain = BusiConf.BUSI_HTTP_DOMAIN, moduleId = BuildConfig.LIBRARY_PACKAGE_NAME, interceptorClazz = HttpInterceptor::class)
interface BusiHttpService {
    /**
     * 查询闪屏配置
     */
    @POST("/api/getSplash")
    @Headers("Content-Type: application/json;")
    fun getOpSplashList(): Observable<HttpResp<SplashItem>>
}
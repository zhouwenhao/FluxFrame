package com.timark.net

import android.text.TextUtils
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetMgr {
    companion object{
        @Volatile
        private var mSelf : NetMgr? = null

        fun getInstance() : NetMgr{
            if (mSelf == null){
                synchronized(NetMgr::class.java){
                    if (mSelf == null){
                        mSelf = NetMgr()
                    }
                }
            }
            return mSelf!!
        }
    }

    private var mRetrofitMap = mutableMapOf<String, Retrofit>()

    fun<T> getService(clazz: Class<T>) : T{
        if (clazz.isAnnotationPresent(NetDomain::class.java)){
            val netDomain = clazz.getAnnotation(NetDomain::class.java)!!
            val domain = netDomain.domain
            val moduleId = netDomain.moduleId
            val interceptor = netDomain.interceptorClazz

            if (TextUtils.isEmpty(domain) || TextUtils.isEmpty(moduleId)){
                throw Exception("Must set value <NetDomain>")
            }

            var cTime = 40L
            var rTime = 40L
            var wTime = 40L
            if (clazz.isAnnotationPresent(NetTimeOut::class.java)){
                val netTime = clazz.getAnnotation(NetTimeOut::class.java)!!
                cTime = netTime.connectTimeOutSec
                rTime = netTime.readTimeOutSec
                wTime = netTime.writeTimeOutSec
            }

            var retrofit = mRetrofitMap[moduleId]
            if (retrofit == null){
                retrofit = createRetrofit(domain, createHttp(interceptor.java.newInstance(), cTime, rTime, wTime))
                mRetrofitMap[moduleId] = retrofit
            }
            return retrofit.create(clazz)
        } else {
            throw Exception("Must annotation <NetDomain>")
        }
    }

    private fun createHttp(interceptor : NetInterceptor, cTime : Long, rTime : Long, wTime : Long) : OkHttpClient{
        return OkHttpClient().newBuilder()
            .connectTimeout(cTime, TimeUnit.SECONDS)
            .readTimeout(rTime, TimeUnit.SECONDS)
            .writeTimeout(wTime, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private fun createRetrofit(domain : String, client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(domain)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}
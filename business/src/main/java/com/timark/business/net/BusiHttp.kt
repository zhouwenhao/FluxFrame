package com.timark.business.net

import com.timark.business.model.SplashItem
import com.timark.net.NetMgr
import com.timark.sundy.http.HttpResp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BusiHttp {
    companion object{
        fun getOpSplashList(): Observable<HttpResp<SplashItem>> {
            return NetMgr.getInstance().getService(BusiHttpService::class.java).getOpSplashList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
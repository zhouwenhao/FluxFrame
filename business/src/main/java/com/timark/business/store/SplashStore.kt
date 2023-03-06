package com.timark.business.store

import com.timark.business.BusiActions
import com.timark.business.BusiSp
import com.timark.business.model.SplashItem
import com.timark.business.net.BusiHttp
import com.timark.flux.FluxAction
import com.timark.flux.FluxStore
import com.timark.sundy.http.HttpObser
import com.timark.sundy.http.HttpResp

class SplashStore : FluxStore<Any, SplashItem, FluxAction<Any, SplashItem>>(BusiActions.ACTION_SPLASH) {

    override fun onPath(action: FluxAction<Any, SplashItem>) {
        obserView(BusiSp.getSplash())

        BusiHttp.getOpSplashList()
            .subscribe(object : HttpObser<SplashItem>("${System.currentTimeMillis()}", mViewObj!!){
                override fun onSuc(value: HttpResp<SplashItem>) {
                    super.onSuc(value)
                    BusiSp.saveSplash(value.data)

                    obserView(value.data)
                }
            })
    }

}
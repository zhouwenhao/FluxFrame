package com.timark.business

import android.app.Application
import com.timark.business.store.kv.IsCanUseStore
import com.timark.business.store.kv.SaveCanUseStore
import com.timark.business.store.net.SplashStore
import com.timark.cache.CacheSp
import com.timark.flux.Dispatcher
import com.timark.sundy.flux.LoadStore

open class BusiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CacheSp.init(this)
        Dispatcher.addStore(LoadStore())
        Dispatcher.addStore(SplashStore())
        Dispatcher.addStore(IsCanUseStore())
        Dispatcher.addStore(SaveCanUseStore())

    }
}
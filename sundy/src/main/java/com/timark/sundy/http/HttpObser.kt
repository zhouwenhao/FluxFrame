package com.timark.sundy.http

import com.timark.flux.Dispatcher
import com.timark.net.NetObser
import com.timark.sundy.flux.LoadAction
import io.reactivex.disposables.Disposable

abstract class HttpObser<T>(tag : String, private val viewObj : Any) : NetObser<HttpResp<T>>(tag) {

    protected fun needShowLoading() : Boolean{
        return true
    }

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        if (needShowLoading()){
            Dispatcher.dispatch(LoadAction(true), viewObj)
        }
    }

    override fun isSuc(value: HttpResp<T>): Boolean {
        return value.isSuc()
    }

    override fun respByException(e: Throwable?): HttpResp<T> {
        return HttpResp<T>().apply {
            code = "999999"
            msg = "error"
            success = false
        }
    }

    override fun onSuc(value: HttpResp<T>) {
        if (needShowLoading()){
            Dispatcher.dispatch(LoadAction(false), viewObj)
        }
    }

    override fun onFail(error: HttpResp<T>) {
        HttpDealMgr.getDealCall()?.deal(error as HttpResp<Any>)
        if (needShowLoading()){
            Dispatcher.dispatch(LoadAction(false), viewObj)
        }
    }
}
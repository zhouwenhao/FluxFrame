package com.timark.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class NetObser<T>(val mTag : String) : Observer<T> {

    private var mDispose : Disposable? = null

    override fun onSubscribe(d: Disposable) {
        mDispose = d
    }

    override fun onNext(value: T) {
        if (isSuc(value)){
            onSuc(value)
        } else if (isMock()){
            val data = mockData()
            if (data != null){
                if (isSuc(data)){
                    onSuc(data)
                } else {
                    onFail(data)
                }
            } else {
                onError(NullPointerException("mock data is null"))
            }
        } else {
            onFail(value)
        }
    }

    override fun onError(e: Throwable) {
        onFail(respByException(e))
    }

    override fun onComplete() {
        mDispose = null
    }

    abstract fun isSuc(value: T) : Boolean
    abstract fun respByException(e : Throwable?) : T
    abstract fun onSuc(value : T)
    abstract fun onFail(error : T)

    fun cancel(){
        mDispose?.dispose()
    }

    protected open fun isMock() : Boolean{
        return BuildConfig.DEBUG
    }

    protected open fun mockData() : T?{
        return null
    }
}
package com.timark.flux

import android.app.Activity
import android.app.Fragment
import android.os.Handler
import android.os.Looper

abstract class FluxStore<TQ, TP, TA : FluxAction<TQ, TP>>(val mAction : String) {
    companion object{
        val mMainHandler = Handler(Looper.getMainLooper())
    }

    private var mBAction : TA? = null
    protected var mViewObj : Any? = null

    fun patch(action : TA, viewObj : Any){
        mBAction = action
        mViewObj = viewObj
        onPath(mBAction!!)
    }

    abstract fun onPath(action : TA)

    protected fun obserView(value : TP?){
        mBAction?.mResp = value
        mViewObj?.let {
            if (it is Activity && (it.isDestroyed || it.isFinishing)){
                return
            }
            if (it is Fragment && (it.isDetached)){
                return
            }
            for (method in it::class.java.declaredMethods){
                if (method.isAnnotationPresent(FluxObser::class.java)){
                    val fluxObser = method.getAnnotation(FluxObser::class.java)
                    if (fluxObser?.action == mAction) {
                        method.isAccessible = true
                        if (fluxObser.curThread) {
                            method.invoke(it, mBAction)
                        } else {
                            mMainHandler.post {
                                method.invoke(it, mBAction)
                            }
                        }
                        break
                    }
                }
            }
        }

    }
}
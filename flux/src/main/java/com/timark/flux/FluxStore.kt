package com.timark.flux

import android.app.Activity
import android.app.Fragment
import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference
import java.lang.reflect.Method

abstract class FluxStore<TQ, TP, TA : FluxAction<TQ, TP>>(val mAction : String) {
    companion object{
        val mMainHandler = Handler(Looper.getMainLooper())
    }

    private var mBAction : TA? = null
    protected var mViewWeakObj : WeakReference<Any>? = null

    fun patch(action : TA, viewObj : Any?){
        mBAction = action
        mViewWeakObj = WeakReference<Any>(viewObj)
        onPath(mBAction!!)
    }

    abstract fun onPath(action : TA)

    protected fun obserView(value : TP?){
        mBAction?.mResp = value
        mViewWeakObj?.get()?.let {
            if (it is Activity && (it.isDestroyed || it.isFinishing)){
                return
            }
            if (it is Fragment && (it.isDetached)){
                return
            }
            var curMethod : Method? = null
            /**
             * <doc>方法筛选，优先选择子类自身的方法；如果没有，再查找父类的public方法</doc>
             */
            curMethod = findMethod(it::class.java.declaredMethods)//不包括父类方法
            if (curMethod == null){
                curMethod = findMethod(it::class.java.methods)//包括父类的所有public方法
            }
            curMethod?.let { rt ->
                rt.isAccessible = true
                val fluxObser = rt.getAnnotation(FluxObser::class.java)
                if (fluxObser?.curThread == true) {
                    rt.invoke(it, mBAction)
                } else {
                    mMainHandler.post {
                        rt.invoke(it, mBAction)
                    }
                }
            }
        }
    }

    private fun findMethod(methods : Array<Method>) : Method?{
        mBAction?.let {
            for (method in methods){
                if (method.isAnnotationPresent(FluxObser::class.java)){
                    val fluxObser = method.getAnnotation(FluxObser::class.java)
                    if (fluxObser?.action == mAction) {
                        val paramsTypes = method.parameterTypes
                        if (paramsTypes.size == 1 && paramsTypes[0] == it.javaClass) {
                            return method
                        }
                    }
                }
            }
        }
        return null
    }
}
package com.timark.flux.single

import com.timark.flux.RootStore

abstract class SingleStore<TQ, TP, TA : SingleAction<TQ, TP>>(action : String) : RootStore(action) {

    private var mBAction : TA? = null

    fun patch(action : TA) : TP?{
        mBAction = action
        val tp = onPath(mBAction!!)
        mBAction?.mResp = tp
        return tp
    }

    abstract fun onPath(action : TA) : TP?
}
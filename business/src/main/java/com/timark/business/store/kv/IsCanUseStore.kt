package com.timark.business.store.kv

import com.timark.business.BusiActions
import com.timark.business.local.BusiSp
import com.timark.flux.single.SingleAction
import com.timark.flux.single.SingleStore

class IsCanUseStore : SingleStore<Any, Boolean, SingleAction<Any, Boolean>>(BusiActions.ACTION_IS_CAN_USE){

    override fun onPath(action: SingleAction<Any, Boolean>): Boolean? {
        return BusiSp.isCanUse()
    }

}
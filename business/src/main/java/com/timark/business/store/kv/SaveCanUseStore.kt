package com.timark.business.store.kv

import com.timark.business.BusiActions
import com.timark.business.local.BusiSp
import com.timark.flux.single.SingleAction
import com.timark.flux.single.SingleStore

class SaveCanUseStore : SingleStore<Any, Any, SingleAction<Any, Any>>(BusiActions.ACTION_SAVE_CAN_USE){

    override fun onPath(action: SingleAction<Any, Any>): Any? {
        BusiSp.saveCanUse()
        return null
    }

}
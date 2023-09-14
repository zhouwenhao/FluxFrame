package com.timark.business.local

import com.timark.cache.CacheSp

class BusiSp {

    companion object{

        @Volatile
        private var mSp : CacheSp? = null
        private final val KEY_IS_CAN_USE = "key_is_can_use"

        fun getKv() : CacheSp{
            if (mSp == null){
                synchronized(BusiSp.javaClass){
                    if (mSp == null){
                        mSp = CacheSp("BusiSp")
                    }
                }
            }
            return CacheSp("BusiSp")
        }

        fun isCanUse() : Boolean{
            return getKv().getBoolean(KEY_IS_CAN_USE, false)
        }

        fun saveCanUse() {
            getKv().putBoolean(KEY_IS_CAN_USE, true)
        }
    }
}
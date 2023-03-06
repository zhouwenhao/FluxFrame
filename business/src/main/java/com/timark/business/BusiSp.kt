package com.timark.business

import com.blankj.utilcode.util.GsonUtils
import com.timark.business.model.SplashItem
import com.timark.cache.CacheSp

class BusiSp {

    companion object{

        private const val KEY_SPLASH = "key_splash"

        @Volatile
        private var mSp : CacheSp? = null

        private fun getSp() : CacheSp{
            if (mSp == null){
                synchronized(BusiSp::class.java){
                    if (mSp == null){
                        mSp = CacheSp("busi_sp")
                    }
                }
            }
            return mSp!!
        }

        fun saveSplash(value : SplashItem?){
            getSp().putString(KEY_SPLASH, GsonUtils.toJson(value))
        }

        fun getSplash() : SplashItem?{
            return GsonUtils.fromJson(getSp().getString(KEY_SPLASH, "{}"), SplashItem::class.java)
        }
    }
}
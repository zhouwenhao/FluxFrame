package com.timark.sundy.http

class HttpDealMgr {
    companion object{
        @Volatile
        private var mDealCall : HttpDealCall? = null

        fun registCall(dealCall: HttpDealCall){
            mDealCall = dealCall
        }

        fun getDealCall() : HttpDealCall?{
            return mDealCall
        }
    }
}
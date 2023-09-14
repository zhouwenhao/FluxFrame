package com.timark.flux

import android.util.Log
import com.timark.flux.loop.FluxAction
import com.timark.flux.loop.FluxStore
import com.timark.flux.single.SingleAction
import com.timark.flux.single.SingleStore


class Dispatcher {
    companion object{
        private val TAG = "Dispatcher";
        private val mStoreMap = mutableMapOf<String, RootStore>()

        fun addStore(args : RootStore){
            if (mStoreMap[args.mAction] != null){
                throw SecurityException(args.mAction + " has regsterd")
            }
            mStoreMap[args.mAction] = args
        }

//        fun<TQ, TP> addStore(args : FluxStore<TQ, TP, FluxAction<TQ, TP>>){
//            if (mStoreMap[args.mAction] != null){
//                throw SecurityException(args.mAction + " has regsterd")
//            }
//            mStoreMap[args.mAction] = args
//        }

        fun<TQ, TP, TV : Any> dispatch(action : FluxAction<TQ, TP>, obj : TV?){
            mStoreMap[action.mAction]?.let {
                if (it is FluxStore<*, *, *>){
                    (it as FluxStore<TQ, TP, FluxAction<TQ, TP>>).patch(action, obj)
                } else {
                    Log.e(TAG, it.javaClass.name + " can not cast FluxStore")
                }
            }
        }

        fun<TQ, TP: Any> single(action: SingleAction<TQ, TP>) : TP?{
            if (mStoreMap[action.mAction] != null){
                val rootStore = mStoreMap[action.mAction]
                if (rootStore is SingleStore<*, *, *>){
                    return (rootStore as SingleStore<TQ, TP, SingleAction<TQ, TP>>).patch(action)
                } else {
                    Log.e(TAG, rootStore!!.javaClass.name + " can not cast SingleStore")
                    return null
                }
            } else {
                Log.e(TAG, action!!.javaClass.name + " this store is null")
                return null
            }
        }
    }
}
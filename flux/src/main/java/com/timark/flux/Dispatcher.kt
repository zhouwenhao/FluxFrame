package com.timark.flux


class Dispatcher {
    companion object{

        private val mStoreMap = mutableMapOf<String, FluxStore<*, *, *>>()

        fun<TQ, TP> addStore(args : FluxStore<TQ, TP, FluxAction<TQ, TP>>){
            mStoreMap[args.mAction] = args
        }

        fun<TQ, TP, TV : Any> dispatch(action : FluxAction<TQ, TP>, obj : TV){
            mStoreMap[action.mAction]?.let {
                (it as FluxStore<TQ, TP, FluxAction<TQ, TP>>).patch(action, obj)
            }
        }
    }
}
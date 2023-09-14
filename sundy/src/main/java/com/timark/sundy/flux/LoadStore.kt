package com.timark.sundy.flux

import com.timark.flux.loop.FluxAction
import com.timark.flux.loop.FluxStore


class LoadStore : FluxStore<Boolean, Boolean, FluxAction<Boolean, Boolean>>(SundyFluxActions.ACTION_LOAD) {

    override fun onPath(action: FluxAction<Boolean, Boolean>) {
        obserView(action.mReq)
    }

}
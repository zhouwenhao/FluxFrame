package com.timark.sundy.flux

import com.timark.flux.loop.FluxAction

class LoadAction(private val isLoad : Boolean) : FluxAction<Boolean, Boolean>(SundyFluxActions.ACTION_LOAD, isLoad, isLoad) {
}
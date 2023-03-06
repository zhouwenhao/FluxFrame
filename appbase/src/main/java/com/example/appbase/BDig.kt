package com.example.appbase

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.timark.flux.FluxObser
import com.timark.sundy.flux.LoadAction
import com.timark.sundy.flux.SundyFluxActions

open class BDig(ctx : Context) : Dialog(ctx) {

    @FluxObser(action = SundyFluxActions.ACTION_LOAD)
    private fun loadDialogState(action : LoadAction){
        if (action.mResp == true){
            getAct()?.showLoad()
        } else {
            getAct()?.disLoad()
        }
    }

    fun getAct() : BAct? {
        return if (context is BAct){
            context as BAct
        } else {
            null
        }
    }
}
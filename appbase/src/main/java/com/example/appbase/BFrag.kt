package com.example.appbase

import androidx.fragment.app.Fragment
import com.timark.flux.loop.FluxObser
import com.timark.sundy.flux.LoadAction
import com.timark.sundy.flux.SundyFluxActions

open class BFrag : Fragment() {
    @FluxObser(action = SundyFluxActions.ACTION_LOAD)
    private fun loadDialogState(action : LoadAction){
        if (action.mResp == true){
            getAct()?.showLoad()
        } else {
            getAct()?.disLoad()
        }
    }

    fun getAct() : BAct? {
        return activity as BAct?
    }
}
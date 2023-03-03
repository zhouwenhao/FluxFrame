package com.timark.sundy.ui

import androidx.appcompat.app.AppCompatActivity
import com.timark.flux.FluxObser
import com.timark.sundy.flux.LoadAction
import com.timark.sundy.flux.SundyFluxActions

class SundyAct : AppCompatActivity() {

    @FluxObser(action = SundyFluxActions.ACTION_LOAD)
    private fun loadDialogState(action : LoadAction){
        if (action.mResp == true){

        } else {

        }
    }

    fun showLoad(){

    }

    fun disLoad(){

    }
}
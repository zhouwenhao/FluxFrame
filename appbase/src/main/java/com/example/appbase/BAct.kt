package com.example.appbase

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.timark.flux.FluxObser
import com.timark.sundy.flux.LoadAction
import com.timark.sundy.flux.SundyFluxActions

open class BAct : AppCompatActivity() {

    @FluxObser(action = SundyFluxActions.ACTION_LOAD)
    private fun loadDialogState(action : LoadAction){
        Log.d("ttttttttt", "loadDialogState ${action.mResp}")
        if (action.mResp == true){
            showLoad()
        } else {
            disLoad()
        }
    }

    fun showLoad(){
        if (isAlive()){

        }
    }

    fun disLoad(){
        if (isAlive()){

        }
    }

    fun isAlive() : Boolean{
        return !isDestroyed && !isFinishing
    }
}
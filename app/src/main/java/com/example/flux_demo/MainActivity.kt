package com.example.flux_demo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.appbase.BAct
import com.timark.business.BusiActions
import com.timark.business.action.SplashAction
import com.timark.flux.Dispatcher
import com.timark.flux.FluxObser

class MainActivity : BAct() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.test).setOnClickListener{
            Dispatcher.dispatch(SplashAction(), this)
        }
    }

    @FluxObser(BusiActions.ACTION_SPLASH)
    fun onTest(action : SplashAction){
        Log.d("ttttttttt", "onSplash ${action.mResp}")
    }
}
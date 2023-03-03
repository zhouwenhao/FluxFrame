package com.example.flux_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.timark.flux.Dispatcher
import com.timark.flux.FluxObser
import com.timark.sundy.flux.LoadAction
import com.timark.sundy.flux.LoadStore
import com.timark.sundy.flux.SundyFluxActions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Dispatcher.addStore(LoadStore())
        Dispatcher.addStore(TestStore())

        findViewById<TextView>(R.id.test).setOnClickListener{
            Dispatcher.dispatch(TestAction(), this)
        }
    }

    @FluxObser(SundyFluxActions.ACTION_LOAD, false)
    fun onLoad(action : LoadAction){
        Log.d("ttttttttt", "onLoad ${action.mResp}")
    }

    @FluxObser(AppActions.ACTION_TEST)
    fun onTest(action : TestAction){
        Log.d("ttttttttt", "onTest ${action.mResp}")
    }
}
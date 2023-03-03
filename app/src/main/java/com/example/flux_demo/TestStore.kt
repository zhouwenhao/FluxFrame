package com.example.flux_demo

import android.os.Handler
import android.os.Looper
import com.example.flux_demo.model.TestReq
import com.example.flux_demo.model.TestResp
import com.timark.flux.Dispatcher
import com.timark.flux.FluxAction
import com.timark.flux.FluxStore
import com.timark.sundy.flux.LoadAction

class TestStore : FluxStore<TestReq, TestResp, FluxAction<TestReq, TestResp>>(AppActions.ACTION_TEST) {

    private val handler = Handler(Looper.getMainLooper())
    override fun onPath(action: FluxAction<TestReq, TestResp>) {
        Dispatcher.dispatch(LoadAction(true), mViewObj!!)
        action.mResp?.testRes1 = "resp"
        handler.postDelayed({
            obserView()
            Dispatcher.dispatch(LoadAction(false), mViewObj!!)
        }, 2000)
    }

}
package com.example.flux_demo

import com.example.flux_demo.model.TestReq
import com.example.flux_demo.model.TestResp
import com.timark.flux.FluxAction

class TestAction : FluxAction<TestReq, TestResp>(AppActions.ACTION_TEST, TestReq(), TestResp()) {
}
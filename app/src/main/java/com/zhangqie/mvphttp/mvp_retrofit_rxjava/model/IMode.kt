package com.zhangqie.mvphttp.mvp_retrofit_rxjava.model

/**
 * Created by zhangqie on 2017/6/28.
 */
interface IMode {

    fun onRequest(url: String,map: Map<String,String>, callBackListenter: CallBackListenter)

    interface CallBackListenter{
        fun onDataCallBackListenter(data: String)
        fun onError(error: String)
    }

}
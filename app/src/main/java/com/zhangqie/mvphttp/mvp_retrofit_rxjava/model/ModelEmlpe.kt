package com.zhangqie.mvphttp.mvp_retrofit_rxjava.model

import com.zhangqie.mvphttp.api.ApiManager
import rx.Subscriber

/**
 * Created by zhangqie on 2017/6/28.
 */

class ModelEmlpe : BaseModel(),IMode{

    var strApiService = ApiManager.getInstance().strApiService
    override fun onRequest(url: String, map: Map<String, String>, callBackListenter: IMode.CallBackListenter) {
        addSubscription(strApiService.getDetailContent(map), object : Subscriber<String>() {
            override fun onCompleted() {

            }

            override fun onNext(result: String) {
                callBackListenter.onDataCallBackListenter(result)

            }

            override fun onStart() {
                super.onStart()
            }

            override fun onError(e: Throwable) {
                callBackListenter.onError("数据加载失败")
            }
        })
    }



}
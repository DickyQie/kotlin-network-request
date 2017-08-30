package com.zhangqie.mvphttp.mvp_okhttp.presenter

import com.zhangqie.mvphttp.api.Api
import com.zhangqie.mvphttp.mvp_okhttp.model.IMode
import com.zhangqie.mvphttp.mvp_okhttp.model.ModelEmlpe
import com.zhangqie.mvphttp.mvp_okhttp.view.IView

/**
 * Created by zhangqie on 2017/6/28.
 */

class PresenterEmlpe : BasePresenter<IView>(){

    var model: IMode? = null
    init {
        model = ModelEmlpe()
    }
    fun onRequest(map: Map<String,String>){
        var iView = getView()
        if (model != null && iView != null) {

            //验证网络  无网络不加载
            if (false){
                iView.onNetWrok()
                return
            }
            iView.onLoadContributorStart()//初始化
            model!!.onRequest(Api.url, map, object : IMode.CallBackListenter {

                override fun onDataCallBackListenter(data: String) {
                   iView.onLoadContributorComplete(data)

                }
                override fun onError(error: String) {
                    iView.onError(error)
                }

            })
        }

    }

    override fun detachView() {
        super.detachView()
        if (model != null){
            model = null
        }
    }


}
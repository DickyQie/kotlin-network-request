package com.zhangqie.mvphttp.mvp_okhttp.model

import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

/**
 * Created by zhangqie on 2017/6/28.
 */

class ModelEmlpe : IMode{

    override fun onRequest(url: String, map: Map<String, String>,callBackListenter: IMode.CallBackListenter) {

        OkHttpUtils.post().url(url).params(map).build().execute(object : StringCallback() {
            override fun onError(call: okhttp3.Call, e: Exception, id: Int) {
                callBackListenter.onError("加载数据失败")
            }

            override fun onResponse(response: String, id: Int) {
                callBackListenter.onDataCallBackListenter(response)
            }
        })
    }
}

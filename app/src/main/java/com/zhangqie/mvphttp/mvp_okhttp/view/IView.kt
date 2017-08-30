package com.zhangqie.mvphttp.mvp_okhttp.view

/**
 * Created by zhangqie on 2016/8/26.
 */
interface IView {

    fun onLoadContributorStart()

    fun onLoadContributorComplete(data: String)

    fun onNetWrok()

    fun onError(error: String)


}
package com.zhangqie.mvphttp.mvp_okhttp

import android.util.Log
import com.alibaba.fastjson.JSON
import com.zhangqie.mvphttp.R
import com.zhangqie.mvphttp.api.Api
import com.zhangqie.mvphttp.entity.EntityTitle
import com.zhangqie.mvphttp.mvp_okhttp.base.BaseActivity
import com.zhangqie.mvphttp.mvp_okhttp.presenter.PresenterEmlpe
import com.zhangqie.mvphttp.mvp_okhttp.view.IView
import kotlinx.android.synthetic.main.activity_mvp_ok_http.*
import org.jetbrains.anko.toast

class MvpOkHttpActivity : BaseActivity<IView,PresenterEmlpe>(),IView {

    override fun setMainLayout(): Int {
        return R.layout.activity_mvp_ok_http
    }

    override fun createPresenter(): PresenterEmlpe {
        return PresenterEmlpe()
    }

    override fun initView() {
        text.text="加载中......"
    }

    override fun initBeforeData() {
      btn.setOnClickListener {
          p!!.onRequest(Api.showRequestData("15","5"))
      }
    }

    override fun onLoadContributorStart() {
        toast("加载中......")
    }

    override fun onLoadContributorComplete(data: String) {
        Log.i("data",data)
        toast(data)
        //解析数据
        val entityTitle = JSON.parseObject(data, EntityTitle::class.java)
        if (entityTitle.data != null){
            text.text=entityTitle!!.data!!.get(0).title
        }

    }

    override fun onNetWrok() {
        toast("网络未连接")
    }

    override fun onError(error: String) {
        toast(error)
    }

}


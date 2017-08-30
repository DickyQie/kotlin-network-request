package com.zhangqie.mvphttp.mvp_retrofit_rxjava.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.presenter.BasePresenter

/**
 * Created by zhangqie on 2017/6/28.
 */
abstract class BaseActivity<V,T : BasePresenter<V>> : AppCompatActivity(){

    var p: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setMainLayout())
        p = createPresenter()
        p!!.attachView(this as V)
        initView()
        initBeforeData()
    }

    abstract fun setMainLayout(): Int

    abstract fun createPresenter(): T

    abstract fun initView()

    abstract fun initBeforeData()

    override fun onDestroy() {
        p!!.detachView()//销毁---避免内存泄漏
        super.onDestroy()
    }

}
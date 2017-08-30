package com.zhangqie.mvphttp.mvp_okhttp.presenter

import java.lang.ref.WeakReference

/**
 * Created by zhangqie on 2017/6/28.
 */
open class BasePresenter<T> {

    var weakReference: WeakReference<T>? = null

    fun attachView(t: T){
        weakReference = WeakReference<T>(t)
    }

    open fun detachView(){
        if (weakReference != null){
            weakReference!!.clear()
            weakReference = null
        }
    }

    fun getView(): T?{
        return weakReference!!.get()
    }

}
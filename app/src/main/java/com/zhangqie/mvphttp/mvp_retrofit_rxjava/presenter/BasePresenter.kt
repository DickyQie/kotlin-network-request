package com.zhangqie.mvphttp.mvp_retrofit_rxjava.presenter

import android.util.Log
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
        Log.i("1","111111")
        if (weakReference != null){
            weakReference!!.clear()
            weakReference = null
        }
    }

    fun getView(): T?{
        return weakReference!!.get()
    }

}
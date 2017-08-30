package com.zhangqie.ko1.mvp_retrofit_rxjava.api

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by zhangqie on 2016/8/26.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("app.php")
    abstract fun getDetailContent(@FieldMap params: Map<String, String>): Observable<String>

    @FormUrlEncoded
    @POST("app.php")
    abstract fun getCityInfo(@FieldMap params: Map<String, String>): Call<String>


}
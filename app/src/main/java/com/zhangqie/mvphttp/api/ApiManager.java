package com.zhangqie.mvphttp.api;



import com.zhangqie.ko1.mvp_retrofit_rxjava.api.ApiService;
import com.zhangqie.mvphttp.converter.FastJsonConverterFactory;
import com.zhangqie.mvphttp.converter.StringConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by zhangqie on 2016/8/26.
 */

public class ApiManager {
    /**
     * 这个是返回json数据(是对象）时请求所用的对象
     */
    private ApiService jsonApiService;

    /**
     * //这个是返回json是string数据时请求所用的对象
     */
    private ApiService strApiService;

    private static volatile ApiManager instance=null;
    //在访问HttpMethods时创建单例
    private static final class SingletonHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }
    //获取单例
    public static ApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public ApiManager() {
        jsonApiService = getJsonServiceInstance();
        strApiService = getStrServiceInstance();
    }
    public ApiService getJsonApiService() {
        return jsonApiService;
    }

    public ApiService getStrApiService() {
        return strApiService;
    }


    private ApiService getJsonServiceInstance() {
        if (null == jsonApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain
                            .request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.Companion.getUrl())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            jsonApiService = retrofit.create(ApiService.class);
        }

        return jsonApiService;
    }


    private ApiService getStrServiceInstance() {
        if (null == strApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain
                            .request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.Companion.getUrl())
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            strApiService = retrofit.create(ApiService.class);
        }

        return strApiService;
    }

}

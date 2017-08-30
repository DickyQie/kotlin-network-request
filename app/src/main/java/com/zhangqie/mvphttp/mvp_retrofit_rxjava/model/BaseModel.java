package com.zhangqie.mvphttp.mvp_retrofit_rxjava.model;



import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqie on 2016/8/26.
 */

public class BaseModel {

    private CompositeSubscription compositeSubscription;
    public void detachModel() {
        onUnsubscribe();
    }
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        Log.i("3","333333");
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
    /***
     * 注册
     * @param subscription
     */
    public void addSubscription(Subscription subscription)
    {
        if (compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }
    /***
     * 注册
     */
    public void addSubscription(Observable observable, Subscriber subscriber)
    {
        if (compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /***
     * 注册
     */
    public void addSubscription(Observable observable, Func1 func1, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .map(func1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /***
     * 注册
     */
    public void addSubscriptionFlatMap(Observable observable, Func1 func1, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .flatMap(func1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}

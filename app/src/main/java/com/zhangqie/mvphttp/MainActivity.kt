package com.zhangqie.mvphttp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zhangqie.mvphttp.mvp_okhttp.MvpOkHttpActivity
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.MvpRetrofitRxJavaActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView(){
        var intent: Intent? = null
        btn1.setOnClickListener {
            intent= Intent(this,MvpOkHttpActivity::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            intent= Intent(this,MvpRetrofitRxJavaActivity::class.java)
            startActivity(intent)
        }
    }
}

# kotlin for android----------MVP模式下（OKHttp和 Retrofit+RxJava）网络请求的两种实现方式 
 <p>今天要说的干货是：以Kotlin，在MVP模式下（OKHttp和 Retrofit+RxJava）网络请求两种实现方式的一个小案例，希望对大家有所帮助，效果图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2017.cnblogs.com/blog/1041439/201708/1041439-20170830171003530-87916400.gif"></p> 
<p>&nbsp;<br> Retrofit是Square公司开发的一款针对Android网络请求的一个当前很流行的网络请求库。可参考博客看详细介绍：<a href="http://www.cnblogs.com/zhangqie/p/6368208.html" rel="nofollow">Android开发之Retrofit+RxJava的使用</a></p> 
<p><span style="color:#000000">完成以上功能需引入：</span></p> 
<pre><code class="language-java">    compile "com.android.support:appcompat-v7:$support_version"
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    compile "org.jetbrains.anko:anko-common:$anko_version"
    compile 'com.zhy:okhttputils:2.6.2'
    compile 'com.alibaba:fastjson:1.1.54.android'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'io.reactivex:rxjava:1.1.6'</code></pre> 
<pre><code class="language-java">class MvpOkHttpActivity : BaseActivity&lt;IView,PresenterEmlpe&gt;(),IView {

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

}</code></pre> 
<p>可见代码是相当比较少的，以MVP模式来完成的，主要有Model,Presenter,View</p> 
<p>&nbsp;</p> 
<p>Model:</p> 
<pre><code class="language-java">class ModelEmlpe : IMode{

    override fun onRequest(url: String, map: Map&lt;String, String&gt;,callBackListenter: IMode.CallBackListenter) {

        OkHttpUtils.post().url(url).params(map).build().execute(object : StringCallback() {
            override fun onError(call: okhttp3.Call, e: Exception, id: Int) {
                callBackListenter.onError("加载数据失败")
            }

            override fun onResponse(response: String, id: Int) {
                callBackListenter.onDataCallBackListenter(response)
            }
        })
    }
}</code></pre> 
<p>Presenter</p> 
<pre><code class="language-java">class PresenterEmlpe : BasePresenter&lt;IView&gt;(){

    var model: IMode? = null
    init {
        model = ModelEmlpe()
    }
    fun onRequest(map: Map&lt;String,String&gt;){
        var iView = getView()
        if (model != null &amp;&amp; iView != null) {

            //验证网络  无网络不加载
            if (false){
                iView.onNetWrok()
                return
            }
            iView.onLoadContributorStart()//初始化
            model!!.onRequest(Api.url, map, object : IMode.CallBackListenter {

                override fun onDataCallBackListenter(data: String) {
                   iView.onLoadContributorComplete(data)

                }
                override fun onError(error: String) {
                    iView.onError(error)
                }

            })
        }

    }

    override fun detachView() {
        super.detachView()
        if (model != null){
            model = null
        }
    }


}</code></pre> 
<p>当然解析数据也可以不用在Activity中完成，可以在我们的Presenter或Model中完成也是可以的，</p> 
<p>&nbsp;</p> 
<p>我们的RxJava+Retrofit 提供了请求时就直接解析的功能，这点也可以好好运用。</p> 
<p>&nbsp;</p> 
<p>两种请求方式的Activity，Presenter和View 其实是差不多的，就是Model层 实现的方式不一样而已。</p> 

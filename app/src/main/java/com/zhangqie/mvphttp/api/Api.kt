package com.zhangqie.mvphttp.api

/**
 * Created by zhangqie on 2017/6/28.
 */
open class Api {

    companion object{
        var url: String = "http://bajie.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1"

        fun showRequestData(gid: String, num: String): Map<String, String> {
            val map = HashMap<String,String>()
            map.put("c", "ad")
            map.put("a", "getlist")
            map.put("gid", gid)
            map.put("num", num)
            return map
        }
    }
}
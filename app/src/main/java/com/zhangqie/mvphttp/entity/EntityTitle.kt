package com.zhangqie.mvphttp.entity


class EntityTitle : User() {
    var data: List<Data>? = null

    inner class Data {
        var id: String? = null

        var gid: String? = null

        var title: String? = null

        var image: String? = null

        var dataid: String? = null

        var datatype: String? = null

        var displayorder: String? = null

        var description: String? = null

        var available: String? = null

        var url: String? = null
    }


}

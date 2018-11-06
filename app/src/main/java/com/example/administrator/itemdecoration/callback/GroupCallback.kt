package com.example.administrator.itemdecoration.callback

import com.example.administrator.itemdecoration.bean.GroupInfo

/**
 * Created by lengwei on 2017/11/11.
 *
 * @Description
 */
interface GroupCallback {

    fun group(position: Int): GroupInfo

}

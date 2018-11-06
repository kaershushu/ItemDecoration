package com.example.administrator.itemdecoration.callback

import com.example.administrator.itemdecoration.bean.GroupInfo

/**
 * Created by lengwei on 2017/11/11.
 *
 * @Description
 */
class GroupImpl : GroupCallback {

    override fun group(position: Int): GroupInfo {
        val index = position % 5
        val groupId = position / 5
        val info = GroupInfo()
        info.groupSize = 5
        info.groupId = groupId.toString() + ""
        info.position = index
        info.title = groupId.toString() + ""
        return info
    }
}

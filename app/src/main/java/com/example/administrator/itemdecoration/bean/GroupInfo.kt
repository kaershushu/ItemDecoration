package com.example.administrator.itemdecoration.bean

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description
 */
class GroupInfo {

    var groupId: String? = null
    var title: String? = null
    var position: Int = 0
    var groupSize: Int = 0

    val isGroupFirstPosition: Boolean
        get() = position == 0

    val isGroupLastPosition: Boolean
        get() = position == groupSize - 1 && position >= 0
}

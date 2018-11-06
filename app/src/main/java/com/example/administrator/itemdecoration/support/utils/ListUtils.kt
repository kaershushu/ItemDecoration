package com.example.administrator.itemdecoration.support.utils

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by lengwei on 2017/11/11.
 *
 * @Description
 */
object ListUtils {

    val mapList: List<Map<String, String>>
        get() {
            val listItem  = ArrayList<Map<String, String>>()
            val map1 = HashMap<String, String>()
            val map2 = HashMap<String, String>()
            val map3 = HashMap<String, String>()
            val map4 = HashMap<String, String>()
            val map5 = HashMap<String, String>()
            val map6 = HashMap<String, String>()

            map1.put("ItemTitle", "美国谷歌公司已发出");
            map1.put("ItemText", "发件人:谷歌 CEO Sundar Pichai");
            listItem.add(map1)

            map2.put("ItemTitle", "国际顺丰已收入");
            map2.put("ItemText", "等待中转")
            listItem.add(map2)

            map3.put("ItemTitle", "国际顺丰转件中")
            map3.put("ItemText", "下一站中国")
            listItem.add(map3)

            map4.put("ItemTitle", "中国顺丰已收入")
            map4.put("ItemText", "下一站广州华南理工大学")
            listItem.add(map4)

            map5.put("ItemTitle", "中国顺丰派件中")
            map5.put("ItemText", "等待派件")
            listItem.add(map5)

            map6.put("ItemTitle", "华南理工大学已签收")
            map6.put("ItemText", "收件人:Carson")
            listItem.add(map6)
            return listItem
        }

    fun getNumList(position: Int): List<String> {
        val numList = ArrayList<String>()
        for (i in 0 until position) {
            numList.add(i.toString())
        }
        return numList
    }
}

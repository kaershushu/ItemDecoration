package com.example.administrator.itemdecoration.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.itemdecoration.R
import com.example.administrator.itemdecoration.adapter.TimeLineAdapter.VH

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description
 */
class TimeLineAdapter(private val mMapList: List<Map<String, String>>) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val content = LayoutInflater.from(parent.context).inflate(R.layout.time_line_item_layout, parent, false)
        return VH(content)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val map = mMapList[position]
        holder.tvLocation.text = map["ItemTitle"]
        holder.tvNext.text = map["ItemText"]
    }

    override fun getItemCount(): Int {
        return mMapList.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvLocation: TextView = itemView.findViewById(R.id.location_tv)
        internal var tvNext: TextView = itemView.findViewById(R.id.next_tv)
    }
}

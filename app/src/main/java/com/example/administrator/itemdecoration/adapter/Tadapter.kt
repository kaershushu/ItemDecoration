package com.example.administrator.itemdecoration.adapter

import com.example.administrator.itemdecoration.R
import com.example.administrator.itemdecoration.adapter.Tadapter.VH

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by lengwei on 2017/11/4.
 *
 * @Description
 */
class Tadapter(private val mContext: Context) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tv.text = mContext.getString(R.string.content)
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv: TextView = itemView.findViewById(R.id.tv_content)

    }
}

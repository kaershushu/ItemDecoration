package com.example.administrator.itemdecoration.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.itemdecoration.R
import com.example.administrator.itemdecoration.adapter.GroupAdapter.ViewHolder

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description
 */
class GroupAdapter(private val mData: List<String>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tv: TextView = itemView.findViewById(R.id.tv_content)

    }

}

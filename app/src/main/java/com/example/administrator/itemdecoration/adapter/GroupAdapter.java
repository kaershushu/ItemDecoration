package com.example.administrator.itemdecoration.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.administrator.itemdecoration.R;
import com.example.administrator.itemdecoration.adapter.GroupAdapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description
 */
public class GroupAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<String> mData = new ArrayList<>();

    public GroupAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_content);
        }
    }

}

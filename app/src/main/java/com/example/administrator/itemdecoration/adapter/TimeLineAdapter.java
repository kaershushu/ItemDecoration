package com.example.administrator.itemdecoration.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.administrator.itemdecoration.R;
import com.example.administrator.itemdecoration.adapter.TimeLineAdapter.VH;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description
 */
public class TimeLineAdapter extends RecyclerView.Adapter<VH> {

    private List<Map<String, String>> mMapList = new ArrayList<>();
    public TimeLineAdapter(List<Map<String, String>> mapList) {
        this.mMapList = mapList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_line_item_layout, parent, false);
        return new VH(content);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Map<String, String> map = mMapList.get(position);
        holder.tvLocation.setText(map.get("ItemTitle"));
        holder.tvNext.setText(map.get("ItemText"));
    }

    @Override
    public int getItemCount() {
        return mMapList.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        TextView tvLocation;
        TextView tvNext;
        public VH(View itemView) {
            super(itemView);
            tvLocation = itemView.findViewById(R.id.location_tv);
            tvNext = itemView.findViewById(R.id.next_tv);
        }
    }
}

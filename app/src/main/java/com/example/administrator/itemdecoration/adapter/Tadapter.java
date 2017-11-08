package com.example.administrator.itemdecoration.adapter;

import com.example.administrator.itemdecoration.R;
import com.example.administrator.itemdecoration.adapter.Tadapter.VH;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lengwei on 2017/11/4.
 *
 * @Description
 */
public class Tadapter extends RecyclerView.Adapter<VH> {

    private Context mContext;

    public Tadapter(Context context) {
        this.mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tv.setText(mContext.getString(R.string.content));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.tv_content));
        }
    }
}

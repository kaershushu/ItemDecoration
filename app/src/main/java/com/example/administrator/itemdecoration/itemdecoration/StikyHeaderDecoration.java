package com.example.administrator.itemdecoration.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description
 */
public class StikyHeaderDecoration extends RecyclerView.ItemDecoration {

    public StikyHeaderDecoration() {
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int offset = 0;
        if (parent.getChildAdapterPosition(view) == 0) {
            offset = dipToPx(20, view.getContext());
        } else {

        }
        outRect.set(0, offset, 0, 0);
    }

    private int dipToPx(int dpVal, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.getResources().getDisplayMetrics());
    }

}

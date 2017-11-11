package com.example.administrator.itemdecoration.itemdecoration;

import com.example.administrator.itemdecoration.bean.GroupInfo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by lengwei on 2017/11/11.
 *
 * @Description 粘性头部Decoration
 */
public class StickyHeaderDecoration extends RecyclerView.ItemDecoration {

    private Paint mTextPaint;
    private Drawable mHeaderDrawable;
    private Drawable mDividerDrawable;
    private GroupCallback mCallback;
    private final int MHEADER_HEIGHT = 120;
    private final int MDIVIDER_HEIGHT = 10;

    public StickyHeaderDecoration(Context context, GroupCallback callback) {
        mCallback = callback;
        mHeaderDrawable = ContextCompat.getDrawable(context, android.R.color.holo_red_dark);
        mDividerDrawable = ContextCompat.getDrawable(context, android.R.color.darker_gray);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        mTextPaint.setTextSize(35);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            if (mCallback != null) {
                int left = child.getLeft();
                int right = child.getRight();
                GroupInfo info = mCallback.group(position);
                if (i != 0) {
                    int top = child.getTop() - MHEADER_HEIGHT;
                    if (info.isGroupFirstPosition()) {
                        int bottom = child.getTop();
                        drawTextHeader(c,info, left, top, right, bottom);
                    }
                } else {
                    int top = 0;
                    int bottom = MHEADER_HEIGHT;
                    if (info.isGroupLastPosition()){
                        int suggestTop = child.getBottom() - MHEADER_HEIGHT;
                        if (suggestTop < top){
                            top = suggestTop;
                        }
                        bottom = top + MHEADER_HEIGHT;
                    }
                    drawTextHeader(c,info, left, top,right, bottom);
                }
            }
        }
    }

    private void drawTextHeader(Canvas c,GroupInfo info,int left, int top, int right ,int bottom){
        mHeaderDrawable.setBounds(left, top, right, bottom);
        mHeaderDrawable.draw(c);
        c.drawText(info.getTitle(), left + 20, bottom - 50, mTextPaint);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mCallback != null) {
            int position = parent.getChildAdapterPosition(view);
            GroupInfo info = mCallback.group(position);
            if (info.isGroupFirstPosition()) {
                outRect.set(0, MHEADER_HEIGHT, 0, 0);
            } else {
                outRect.set(0, MDIVIDER_HEIGHT, 0, 0);
            }
        }
    }
}

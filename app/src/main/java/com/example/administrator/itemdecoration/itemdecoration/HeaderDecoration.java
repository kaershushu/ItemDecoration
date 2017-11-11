package com.example.administrator.itemdecoration.itemdecoration;

import com.example.administrator.itemdecoration.callback.GroupCallback;
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
 * Created by lengwei on 2017/11/9.
 *
 * @Description 分组ItemDecoration
 */
public class HeaderDecoration extends RecyclerView.ItemDecoration {

    private GroupCallback mGroupCallback;
    private Drawable mHeaderDrawable;
    private Drawable mDividerDrawable;
    private Paint mTextPaint;
    private final int MHEADERHEIGHT = 120;
    private final int MDIVIDERHEIGHT = 10;

    public HeaderDecoration(Context context, GroupCallback callback) {
        this.mGroupCallback = callback;
        mHeaderDrawable = ContextCompat.getDrawable(context, android.R.color.holo_red_dark);
        mDividerDrawable = ContextCompat.getDrawable(context, android.R.color.darker_gray);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        mTextPaint.setTextSize(35);
    }

    /**
     * onDraw画普通的大标题
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
            GroupInfo info = mGroupCallback.group(index);
            int left, top, right, bottom = 0;
            if (info.isGroupFirstPosition()) {
                left = child.getLeft();
                top = child.getTop() - MHEADERHEIGHT;
                right = child.getRight();
                bottom = child.getTop();
                mHeaderDrawable.setBounds(left, top, right, bottom);
                mHeaderDrawable.draw(c);
                c.drawText(info.getTitle(), left + 20,bottom - 50, mTextPaint);
            } else {
                left = child.getLeft();
                top = child.getTop() - MDIVIDERHEIGHT;
                right = child.getRight();
                bottom = child.getTop();
                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (mGroupCallback != null) {
            GroupInfo groupInfo = mGroupCallback.group(position);
            if (groupInfo.isGroupFirstPosition()) {
                outRect.set(0, MHEADERHEIGHT, 0, 0);
            } else {
                outRect.set(0, MDIVIDERHEIGHT, 0, 0);
            }
        }
    }

}

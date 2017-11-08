package com.example.administrator.itemdecoration.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description 时间轴
 */
public class TimeLineDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Paint mPaint;
    private Paint mTextPaint;
    private int moveX = 250; //Rect left move
    private int moveY = 50;  //Rect top move
    private static final int RADIUS = 12; //半径

    public TimeLineDecoration(Context context) {
        this.mContext = context;
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        this.mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mTextPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        this.mTextPaint.setTextSize(30);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            final int topX = child.getLeft() - moveX / 3;
            final int topY = child.getTop() - moveY;
            final int circleTopX = topX;
            final int circleTopY = child.getTop() + child.getHeight() / 2;
            final int circleX = topX;
            final int circleY = circleTopY + RADIUS;
            final int circleBottomX = topX;
            final int circleBottomY = circleY + RADIUS;
            final int bottomX = topX;
            final int bottomY = child.getBottom();
            c.drawText("时间轴", 50, circleY + 10, mTextPaint);
            c.drawLine(topX, topY, circleTopX, circleTopY, mPaint);
            c.drawCircle(circleX, circleY, RADIUS, mPaint);
            c.drawLine(circleBottomX, circleBottomY, bottomX, bottomY, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = moveX;
        outRect.top = moveY;
    }
}

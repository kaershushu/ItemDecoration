package com.example.administrator.itemdecoration.itemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description 使用onDrawOver绘制分割线
 */
public class OverlayDecoration extends RecyclerView.ItemDecoration {

    private static final int [] ATTRS = new int[]{android.R.attr.listDivider};
    private Context mContext;
    private int orientation = LinearLayoutManager.VERTICAL;
    private Drawable mDrawable;


    private OverlayDecoration(Context context) {
        this.mContext = context;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDrawable = a.getDrawable(0);
        a.recycle();
    }

    public OverlayDecoration(Context context, int orientation) {
        this(context);
        this.orientation = orientation;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        super.onDrawOver(c, parent, state);
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) == parent.getAdapter().getItemCount() - 1){
                continue;
            }

            final RecyclerView.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int bottom = child.getBottom() + lp.bottomMargin + Math.round(child.getTranslationY());
            int top = bottom - mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) == parent.getAdapter().getItemCount() - 1){
                return;
            }

            final RecyclerView.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int right = child.getRight() + lp.rightMargin + Math.round(child.getTranslationY());
            int left = right - mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }
}

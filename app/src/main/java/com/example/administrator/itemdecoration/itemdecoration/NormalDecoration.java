package com.example.administrator.itemdecoration.itemdecoration;

import com.example.administrator.itemdecoration.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by lengwei on 2017/11/4.
 *
 * @Description 普通分割线
 */
public class NormalDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable lineDrawable;
    private Drawable verticalDrawable;
    private int orientation = LinearLayoutManager.VERTICAL;
    private int lineHeight = 0;
    private int lineWidth = 0;

    private NormalDecoration(Context context) {
        this.mContext = context;
        this.lineDrawable = ContextCompat.getDrawable(context, R.color.line);
        this.verticalDrawable = ContextCompat.getDrawable(context, R.color.line);
        this.lineHeight = context.getResources().getDimensionPixelSize(R.dimen._1px);
        this.lineWidth = context.getResources().getDimensionPixelSize(R.dimen._1px);
    }

    public NormalDecoration(Context context, int orientation) {
        this(context);
        this.orientation = orientation;
    }

    /**
     * 绘制Decoration到RecyclerView所在的画布上，绘制在Item底部，执行周期在绘制每一个Item之前
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
        if (this.orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView recyclerView) {
        int size = recyclerView.getChildCount();
        for (int i = 0; i < size; i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams lp = ((LayoutParams) child.getLayoutParams());
            int left = child.getLeft();
            int top = child.getBottom() + lp.bottomMargin;
            int right = child.getRight();
            int bottom = top + lineHeight;
            lineDrawable.setBounds(left, top, right, bottom);
            lineDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView recyclerView) {
        int size = recyclerView.getChildCount();
        for (int i = 0; i < size; i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams lp = ((RecyclerView.LayoutParams) child.getLayoutParams());
            int left = child.getRight() + lp.rightMargin;
            int top = child.getTop();
            int right = left + lineWidth;
            int bottom = child.getBottom();
            verticalDrawable.setBounds(left, top, right, bottom);
            verticalDrawable.draw(c);
        }
    }

    /**
     * 绘制Decoration到RecyclerView所在的画布上，绘制在Item的上方，执行周期在回执Item之后
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 获取每一个Item的偏移，outRect指定了Item嵌入像素的数目，类似padding和margin
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        //第一个元素不绘制顶部分割线
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.top = lineHeight;
        } else {
            outRect.left = lineWidth;
        }
    }
}

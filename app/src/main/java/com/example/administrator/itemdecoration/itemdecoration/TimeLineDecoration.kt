package com.example.administrator.itemdecoration.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.State
import android.view.View

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description 时间轴
 */
class TimeLineDecoration(mContext: Context) : RecyclerView.ItemDecoration() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextPaint: Paint
    /**
     * Rect left move
     */
    private val moveX = 250
    /**
     * Rect top move
     */
    private val moveY = 50

    init {
        this.mPaint.color = ContextCompat.getColor(mContext, android.R.color.holo_red_dark)
        this.mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        this.mTextPaint.color = ContextCompat.getColor(mContext, android.R.color.holo_red_dark)
        this.mTextPaint.textSize = 30f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val topX = child.left - moveX / 3
            val topY = child.top - moveY
            val circleTopY = child.top + child.height / 2
            val circleY = circleTopY + DOT_RADIUS
            val circleBottomY = circleY + DOT_RADIUS
            val bottomY = child.bottom
            c.drawText("时间轴", 50f, (circleY + 10).toFloat(), mTextPaint)
            c.drawLine(topX.toFloat(), topY.toFloat(), topX.toFloat(), circleTopY.toFloat(), mPaint)
            c.drawCircle(topX.toFloat(), circleY.toFloat(), DOT_RADIUS.toFloat(), mPaint)
            c.drawLine(topX.toFloat(), circleBottomY.toFloat(), topX.toFloat(), bottomY.toFloat(), mPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = moveX
        outRect.top = moveY
    }

    companion object {
        /**
         * 半径
         */
        private val DOT_RADIUS = 12
    }
}

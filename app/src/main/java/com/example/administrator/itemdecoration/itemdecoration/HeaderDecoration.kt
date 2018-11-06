package com.example.administrator.itemdecoration.itemdecoration

import com.example.administrator.itemdecoration.callback.GroupCallback

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.State
import android.view.View

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description 分组ItemDecoration
 */
class HeaderDecoration(context: Context, private val mGroupCallback: GroupCallback?) : RecyclerView.ItemDecoration() {
    private val mHeaderDrawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.holo_red_dark)
    private val mDividerDrawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.darker_gray)
    private val mTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mHeaderHeight = 120
    private val mDividerHeight = 10

    init {
        mTextPaint.color = ContextCompat.getColor(context, android.R.color.white)
        mTextPaint.textSize = 35f
    }

    /**
     * onDraw画普通的大标题
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(child)
            val info = mGroupCallback!!.group(index)
            val left: Int
            val top: Int
            val right: Int
            var bottom: Int
            if (info.isGroupFirstPosition) {
                left = child.left
                top = child.top - mHeaderHeight
                right = child.right
                bottom = child.top
                mHeaderDrawable?.setBounds(left, top, right, bottom)
                mHeaderDrawable?.draw(c)
                c.drawText(info.title!!, (left + 20).toFloat(), (bottom - 50).toFloat(), mTextPaint)
            } else {
                left = child.left
                top = child.top - mDividerHeight
                right = child.right
                bottom = child.top
                mDividerDrawable?.setBounds(left, top, right, bottom)
                mDividerDrawable?.draw(c)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (mGroupCallback != null) {
            val groupInfo = mGroupCallback.group(position)
            if (groupInfo.isGroupFirstPosition) {
                outRect.set(0, mHeaderHeight, 0, 0)
            } else {
                outRect.set(0, mDividerHeight, 0, 0)
            }
        }
    }

}

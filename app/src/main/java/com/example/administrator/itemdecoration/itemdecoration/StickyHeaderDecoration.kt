package com.example.administrator.itemdecoration.itemdecoration

import com.example.administrator.itemdecoration.bean.GroupInfo
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
 * Created by lengwei on 2017/11/11.
 *
 * @Description 粘性头部Decoration
 */
class StickyHeaderDecoration(context: Context, private val mCallback: GroupCallback?) : RecyclerView.ItemDecoration() {

    private val mTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mHeaderDrawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.holo_red_dark)
    private val mHeaderHeight = 120
    private val mDividerHeight = 10

    init {
        mTextPaint.color = ContextCompat.getColor(context, android.R.color.white)
        mTextPaint.textSize = 35f
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (mCallback != null) {
                val left = child.left
                val right = child.right
                val info = mCallback.group(position)
                if (i != 0) {
                    val top = child.top - mHeaderHeight
                    if (info.isGroupFirstPosition) {
                        val bottom = child.top
                        drawTextHeader(c, info, left, top, right, bottom)
                    }
                } else {
                    var top = 0
                    var bottom = mHeaderHeight
                    if (info.isGroupLastPosition) {
                        val suggestTop = child.bottom - mHeaderHeight
                        if (suggestTop < top) {
                            top = suggestTop
                        }
                        bottom = top + mHeaderHeight
                    }
                    drawTextHeader(c, info, left, top, right, bottom)
                }
            }
        }
    }

    private fun drawTextHeader(c: Canvas, info: GroupInfo, left: Int, top: Int, right: Int, bottom: Int) {
        mHeaderDrawable?.setBounds(left, top, right, bottom)
        mHeaderDrawable?.draw(c)
        c.drawText(info.title!!, (left + 20).toFloat(), (bottom - 50).toFloat(), mTextPaint)
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mCallback != null) {
            val position = parent.getChildAdapterPosition(view)
            val info = mCallback.group(position)
            if (info.isGroupFirstPosition) {
                outRect.set(0, mHeaderHeight, 0, 0)
            } else {
                outRect.set(0, mDividerHeight, 0, 0)
            }
        }
    }
}

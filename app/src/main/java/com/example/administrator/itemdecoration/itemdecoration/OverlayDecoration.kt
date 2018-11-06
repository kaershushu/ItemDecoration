package com.example.administrator.itemdecoration.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutParams
import android.support.v7.widget.RecyclerView.State

/**
 * Created by lengwei on 2017/11/6.
 *
 * @Description 使用onDrawOver绘制分割线
 */
class OverlayDecoration private constructor(context: Context) : RecyclerView.ItemDecoration() {
    private var orientation = LinearLayoutManager.VERTICAL
    private val mDrawable: Drawable?


    init {
        val a = context.obtainStyledAttributes(ATTRS)
        this.mDrawable = a.getDrawable(0)
        a.recycle()
    }

    constructor(context: Context, orientation: Int) : this(context) {
        this.orientation = orientation
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(c, parent, state)
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val adapter = parent.adapter ?: continue
            if (parent.getChildAdapterPosition(child) == adapter.itemCount - 1) {
                continue
            }

            val lp = child.layoutParams as LayoutParams
            val bottom = child.bottom + lp.bottomMargin + Math.round(child.translationY)
            val top = bottom - mDrawable!!.intrinsicHeight
            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(c)
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val adapter = parent.adapter ?: continue
            if (parent.getChildAdapterPosition(child) == adapter.itemCount - 1) {
                return
            }

            val lp = child.layoutParams as LayoutParams
            val right = child.right + lp.rightMargin + Math.round(child.translationY)
            val left = right - mDrawable!!.intrinsicHeight
            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(c)
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}

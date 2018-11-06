package com.example.administrator.itemdecoration.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutParams
import android.support.v7.widget.RecyclerView.State
import android.view.View
import com.example.administrator.itemdecoration.R

/**
 * Created by lengwei on 2017/11/4.
 *
 * @Description 普通分割线
 */
class NormalDecoration private constructor(context: Context) : RecyclerView.ItemDecoration() {

    private var orientation = LinearLayoutManager.VERTICAL

    var lineHeight: Int = context.resources.getDimensionPixelSize(R.dimen._1px)
    var lineWidth: Int = context.resources.getDimensionPixelSize(R.dimen._1px)
    var lineDrawable = ContextCompat.getDrawable(context, R.color.line)
    var verticalDrawable = ContextCompat.getDrawable(context, R.color.line)

    constructor(context: Context, orientation: Int) : this(context) {
        this.orientation = orientation
    }

    /**
     * 绘制Decoration到RecyclerView所在的画布上，绘制在Item底部，执行周期在绘制每一个Item之前
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        super.onDraw(c, parent, state)
        if (this.orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(c: Canvas, recyclerView: RecyclerView) {
        val size = recyclerView.childCount
        for (i in 0 until size) {
            val child = recyclerView.getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            val left = child.left
            val top = child.bottom + lp.bottomMargin
            val right = child.right
            val bottom = top + lineHeight
            lineDrawable?.setBounds(left, top, right, bottom)
            lineDrawable?.draw(c)
        }
    }

    private fun drawHorizontal(c: Canvas, recyclerView: RecyclerView) {
        val size = recyclerView.childCount
        for (i in 0 until size) {
            val child = recyclerView.getChildAt(i)
            val lp = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + lp.rightMargin
            val top = child.top
            val right = left + lineWidth
            val bottom = child.bottom
            verticalDrawable?.setBounds(left, top, right, bottom)
            verticalDrawable?.draw(c)
        }
    }

    /**
     * 获取每一个Item的偏移，outRect指定了Item嵌入像素的数目，类似padding和margin
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        //第一个元素不绘制顶部分割线
        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.top = lineHeight
        } else {
            outRect.left = lineWidth
        }
    }
}

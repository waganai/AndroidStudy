package com.hubiao.view.module.recyclerview.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class SimpleItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        val TAG: String = SimpleItemDecoration::class.java.simpleName

        fun buildItemDecoration(): SimpleItemDecoration {
            return SimpleItemDecoration()
        }
    }


    private val mDividerBounds = Rect()
    private val mDrawOverPaint = Paint()

    private var mDividerDrawable: Drawable? = null
    private var mDrawOverColor = Color.RED
    private var mDividerColor: Int = Color.TRANSPARENT

    private val mDividerPaint = Paint()
    private var mDrawOverBounds = Rect()

    private var mDividerSize: Int = 0

    private var mOrientation: Int = LinearLayoutManager.VERTICAL

    private var mDrawForLastItem = false
    private var mDrawOver = false

    // Unit pixel

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        when (mOrientation) {
            LinearLayoutManager.VERTICAL -> drawVertical(parent, c)
            LinearLayoutManager.HORIZONTAL -> drawHorizontal(parent, c)
            else -> ""
        }
    }

    private fun drawVertical(parent: RecyclerView, canvas: Canvas) {
        canvas.save()
        val left: Int
        val right: Int

        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.clipToPadding) {
            // 如果 clipToPadding为false，item将可以绘制到RecyclerView的padding上
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount: Int = parent.childCount

        if (mDividerDrawable == null) {
            mDividerPaint.color = mDividerColor
        }

        for (i in 0 until childCount) {
            if (i == childCount - 1 && !mDrawForLastItem) {
                return
            }

            val child = parent.getChildAt(i)
            //返回item的边界，包括margin和decoration四周的距离
            parent.getDecoratedBoundsWithMargins(child, mDividerBounds)
            val bottom: Int = mDividerBounds.bottom + child.translationY.roundToInt()
            val top: Int = bottom - mDividerSize

            if (mDividerDrawable != null) {
                // Drawable绘制的范围：从item的边界(包括margin和decoration四周的距离)底部加上Drawable的高度
                mDividerDrawable?.let {
                    it.setBounds(left, top, right, bottom)
                    it.draw(canvas)
                }
            } else {
                canvas.drawRect(
                    Rect(
                        left,
                        top,
                        right,
                        bottom
                    ), mDividerPaint
                )
            }
        }

        canvas.restore()
    }

    private fun drawHorizontal(parent: RecyclerView, canvas: Canvas) {
        canvas.save()
        val top: Int
        val bottom: Int

        if (parent.clipToPadding) {
            top = parent.top - parent.paddingTop
            bottom = parent.bottom - parent.paddingBottom

            canvas.clipRect(
                parent.left - parent.paddingLeft,
                top,
                parent.right - parent.paddingRight,
                bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount: Int = parent.childCount

        if (mDividerDrawable == null) {
            mDividerPaint.color = mDividerColor
        }

        for (i in 0 until childCount) {
            if (i == childCount - 1 && !mDrawForLastItem) {
                return
            }

            val child = parent.getChildAt(i)
            //返回item的边界，包括margin和decoration四周的距离
            parent.getDecoratedBoundsWithMargins(child, mDividerBounds)
            val right: Int = mDividerBounds.right + child.translationX.roundToInt()
            val left: Int = right - mDividerSize

            if (mDividerDrawable != null) {
                // Drawable绘制的范围：从item的边界(包括margin和decoration四周的距离)底部加上Drawable的高度
                mDividerDrawable?.let {
                    it.setBounds(left, top, right, bottom)
                    it.draw(canvas)
                }
            } else {
                canvas.drawRect(
                    Rect(
                        left,
                        top,
                        right,
                        bottom
                    ), mDividerPaint
                )
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        if (!mDrawOver) {
            return
        }

        mDrawOverPaint.color = mDrawOverColor
        mDrawOverPaint.strokeJoin = Paint.Join.ROUND
        mDrawOverPaint.strokeCap = Paint.Cap.ROUND
        mDrawOverPaint.style = Paint.Style.STROKE

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent[i]
            parent.getDecoratedBoundsWithMargins(child, mDrawOverBounds)

            val centerX = (mDrawOverBounds.left.toFloat() + mDividerBounds.right.toFloat()) / 2
            val centerY = (mDrawOverBounds.top.toFloat() + mDrawOverBounds.bottom.toFloat()) / 2
            val radius: Float = mDrawOverBounds.width().toFloat()
                .coerceAtMost(mDrawOverBounds.height().toFloat()) / 2

            c.drawCircle(centerX, centerY, radius, mDrawOverPaint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (mOrientation) {
            LinearLayoutManager.VERTICAL -> outRect.set(0, 0, 0, mDividerSize)
            LinearLayoutManager.HORIZONTAL -> outRect.set(0, 0, mDividerSize, 0)
            else -> super.getItemOffsets(outRect, view, parent, state)
        }
    }

    fun setDividerDrawable(dividerDrawable: Drawable): SimpleItemDecoration {
        mDividerDrawable = dividerDrawable

        return this@SimpleItemDecoration
    }

    fun setDividerColor(dividerColor: Int): SimpleItemDecoration {
        mDividerColor = dividerColor

        return this@SimpleItemDecoration
    }

    fun setDividerSize(dividerSize: Int): SimpleItemDecoration {
        mDividerSize = dividerSize

        return this@SimpleItemDecoration
    }

    fun setOrientation(orientation: Int): SimpleItemDecoration {
        mOrientation = orientation

        return this@SimpleItemDecoration
    }

    fun setDrawForLastItem(drawForLastItem: Boolean): SimpleItemDecoration {
        mDrawForLastItem = drawForLastItem

        return this@SimpleItemDecoration
    }

    fun setDrawOverColor(color: Int): SimpleItemDecoration {
        mDrawOverColor = color

        return this@SimpleItemDecoration
    }

    fun setDrawOver(drawOver: Boolean): SimpleItemDecoration {
        mDrawOver = drawOver

        return this@SimpleItemDecoration
    }
}
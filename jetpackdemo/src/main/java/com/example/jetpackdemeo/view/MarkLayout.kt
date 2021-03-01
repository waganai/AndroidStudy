package com.example.jetpackdemeo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout

class MarkLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ViewGroup(context, attrs, defStyleAttr) {

    companion object {
        val TAG = MarkLayout::class.simpleName
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 需要先给children测量
        for (i in 0 until childCount) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var width = 0

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var height = 0

        when (widthMode) {
            MeasureSpec.UNSPECIFIED -> width = 0
            MeasureSpec.EXACTLY -> width = widthSize
            MeasureSpec.AT_MOST -> width = calculateWidth(widthSize)
        }

        when (heightMode) {
            MeasureSpec.UNSPECIFIED -> height = 0
            MeasureSpec.EXACTLY -> height = heightSize
            MeasureSpec.AT_MOST -> height = calculateHeight(widthSize, heightSize)
        }

        setMeasuredDimension(width, height)

        Log.e(TAG, "onMeasure(width = $width, height = $height)")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e(TAG, "onLayout()")

        var lineWidth = 0
        var lineChildMaxHeight = 0
        var preLinesTotalHeight = 0
        var lineStartIndex = 0
        var lineChildrenWidth = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (lineWidth + child.measuredWidth >= measuredWidth) {
                Log.e(TAG, "onLayout() 一行结束， i == $i")
                // 如果这行的所有child的宽度之和超标，换行
                // 并布局已经记录的这一行的所有View
                lineChildrenWidth = 0
                for (j in lineStartIndex until i) {
                    val child = getChildAt(j)
                    child.layout(
                        lineChildrenWidth,
                        preLinesTotalHeight + lineChildMaxHeight / 2 - child.measuredHeight / 2,
                        lineChildrenWidth + child.measuredWidth,
                        preLinesTotalHeight + lineChildMaxHeight / 2 + child.measuredHeight / 2
                    )

                    Log.e(TAG, "onLayout($j) layout($lineChildrenWidth, ${lineChildrenWidth + child.measuredWidth})")

                    lineChildrenWidth += child.measuredWidth
                }
                preLinesTotalHeight += lineChildMaxHeight

                lineWidth = 0
                lineChildMaxHeight = 0
                lineStartIndex = i
            }

            lineWidth += child.measuredWidth
            lineChildMaxHeight =
                if (lineChildMaxHeight < child.measuredHeight) child.measuredHeight else lineChildMaxHeight

            if (i == childCount - 1) {
                Log.e(TAG, "onLayout() 最后一行")

                lineChildrenWidth = 0

                for (j in lineStartIndex .. i) {
                    val child = getChildAt(j)
                    child.layout(
                        lineChildrenWidth,
                        preLinesTotalHeight + lineChildMaxHeight / 2 - child.measuredHeight / 2,
                        lineChildrenWidth + child.measuredWidth,
                        preLinesTotalHeight + lineChildMaxHeight / 2 + child.measuredHeight / 2
                    )

                    Log.e(TAG, "onLayout($j) layout($lineChildrenWidth, ${lineChildrenWidth + child.measuredWidth})")

                    lineChildrenWidth += child.measuredWidth
                }
            }

            Log.e(
                TAG,
                "onLayout() i == $i, lineWidth = $lineWidth, lineChildMaxHeight = $lineChildMaxHeight, preLinesTotalHeight = $preLinesTotalHeight"
            )
        }
    }

    private fun calculateWidth(parentWidth: Int): Int {
        var maxWidth = 0
        var width = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (width + child.measuredWidth <= parentWidth) {
                width += child.measuredWidth
            } else {
                width = child.measuredWidth
            }

            if (width > maxWidth) {
                maxWidth = width
            }
        }

        val layout = LinearLayout(context)

        return maxWidth
    }

    private fun calculateHeight(parentWidth: Int, parentHeight: Int): Int {
        var maxWidth = 0
        var width = 0
        var totalHeight = 0
        var height = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (width + child.measuredWidth <= parentWidth) {
                width += child.width
                height = if (height < child.measuredHeight) child.measuredHeight else height
            } else {
                width = child.measuredWidth
                totalHeight += height
            }

            if (width > maxWidth) {
                maxWidth = width
            }
        }

        return totalHeight
    }
}
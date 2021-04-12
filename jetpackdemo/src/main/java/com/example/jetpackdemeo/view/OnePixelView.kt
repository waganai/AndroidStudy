package com.example.jetpackdemeo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class OnePixelView(context: Context, attributeSet: AttributeSet?, defStyle: Int) :
    View(context, attributeSet, defStyle) {

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context) : this(context, null, 0)

    private val mPaint = Paint()
    var mListener: OnePixelViewOnListener? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(1, 1)

        mListener?.onMeasure()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mListener?.onLayout()
    }

    init {
        mPaint.color = Color.TRANSPARENT
    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)

        mListener?.onDraw()
    }

    fun addListener(listener: OnePixelViewOnListener):OnePixelView {
        mListener = listener

        return this
    }

    interface OnePixelViewOnListener {
        fun onMeasure()
        fun onLayout()
        fun onDraw()
    }
}
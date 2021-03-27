package com.example.jetpackdemeo.view.roundviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

class RoundImageView(context: Context, attribute: AttributeSet?, defStyle: Int) :
    androidx.appcompat.widget.AppCompatImageView(context, attribute, defStyle) {

    private val maskPaint: Paint = Paint()
    private val zonePaint: Paint = Paint()
    private val roundRect: RectF = RectF()
    private var radius: Float = 0F

    constructor(context: Context, attribute: AttributeSet?) : this(context, attribute, 0) {
    }

    constructor(context: Context) : this(context, null, 0) {
    }

    init {
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        zonePaint.isAntiAlias = true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        roundRect.set(0.toFloat(), 0.toFloat(), width.toFloat(), height.toFloat())
        radius = width.coerceAtMost(height).toFloat() / 2
    }

    override fun draw(canvas: Canvas?) {
        canvas?.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG)

        canvas?.drawRoundRect(roundRect, radius, radius, zonePaint)

        canvas?.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG)

        super.draw(canvas)

        canvas?.restore()
    }
}
package com.example.jetpackdemeo.view.roundviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

class RoundImageView2(context: Context, attribute: AttributeSet?, defStyle: Int) :
    androidx.appcompat.widget.AppCompatImageView(context, attribute, defStyle) {

    private val maskPaint: Paint = Paint()
    private val zonePaint: Paint = Paint()
    private val roundRect: RectF = RectF()
    private var radius: Float = 0F
    private val clipPath: Path = Path()

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

        clipPath.reset()
        clipPath.addRoundRect(
            RectF(0F, 0F, width.toFloat(), height.toFloat()),
            radius, radius,
            Path.Direction.CW
        )
        clipPath.moveTo(-10F, -10F);  // 通过空操作让Path区域占满画布
        clipPath.moveTo(width + 10F, height + 10F);
    }

    override fun draw(canvas: Canvas?) {
//        canvas?.save()
//        canvas?.clipPath(clipPath)
//
//        canvas?.save()
//
//        super.draw(canvas)
//
//        canvas?.restore()

        val saveCount = canvas?.save() ?: 0
        canvas?.clipPath(clipPath)
        super.draw(canvas)

        canvas?.restoreToCount(saveCount)
    }
}
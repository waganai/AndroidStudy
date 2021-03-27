package com.example.jetpackdemeo.view.roundviews

import android.widget.RelativeLayout
import android.content.Context
import android.graphics.*
import android.util.AttributeSet

class RoundRelativeLayout1(context: Context, attribute: AttributeSet?, defStyle: Int) :
    RelativeLayout(context, attribute, defStyle) {


    private var radius: Float = 0F
    private val clipPath: Path = Path()

    constructor(context: Context, attribute: AttributeSet?) : this(context, attribute, 0) {
    }

    constructor(context: Context) : this(context, null, 0) {
    }

    init {
        clipPath.fillType = Path.FillType.EVEN_ODD
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        radius = width.coerceAtMost(height).toFloat() / 2

        clipPath.reset()
        clipPath.addRoundRect(
            RectF(0F, 0F, width.toFloat(), height.toFloat()),
            radius, radius,
            Path.Direction.CW
        )
//        clipPath.moveTo(-10F, -10F);  // 通过空操作让Path区域占满画布
//        clipPath.moveTo(width + 10F, height + 10F);
    }

    override fun draw(canvas: Canvas?) {
        val saveCount = canvas?.save() ?: 0
        canvas?.clipPath(clipPath)
        super.draw(canvas)

        canvas?.restoreToCount(saveCount)
    }
}
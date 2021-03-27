package com.example.jetpackdemeo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.jetpackdemeo.R


class RounderCornerImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private var mBitmap: Bitmap? = null
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bounds: RectF = RectF()

    private var mWidth = 0
    private var mHeight = 0

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.yileina)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mBitmap?.apply {
            if (mWidth != this@RounderCornerImageView.width ||
                mHeight != this@RounderCornerImageView.height
            ) {
                // 取得想要缩放的matrix参数
                val matrix = Matrix()
                matrix.postScale(
                    this@RounderCornerImageView.width.toFloat() / width,
                    this@RounderCornerImageView.height.toFloat() / height
                )
                // 得到新的图片
                paint.shader = BitmapShader(
                    Bitmap.createBitmap(this, 0, 0, width, height, matrix, true),
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = width.coerceAtMost(height) / 2.toFloat()
//        bounds.set(0.toFloat(), 0.toFloat(), width.toFloat(), height.toFloat())
//        canvas?.drawRoundRect(bounds, radius, radius, paint)
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, paint)
    }
}
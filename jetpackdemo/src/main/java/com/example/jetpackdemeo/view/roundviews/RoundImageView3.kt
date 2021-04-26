package com.example.jetpackdemeo.view.roundviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.jetpackdemeo.R

class RoundImageView3(context: Context, attributeSet: AttributeSet?, defStyle: Int) :
    AppCompatImageView(context, attributeSet, defStyle) {

    val PAINT_FLAGS = Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG
    private val CIRCLE_CROP_PAINT_FLAGS = PAINT_FLAGS or Paint.ANTI_ALIAS_FLAG

    private var mShowBitmap: Bitmap? = null
    private var mContentBitmap: Bitmap? = null
    private val mCanvas = Canvas()

    private val CIRCLE_CROP_SHAPE_PAINT = Paint(CIRCLE_CROP_PAINT_FLAGS)
    private val CIRCLE_CROP_BITMAP_PAINT = Paint(CIRCLE_CROP_PAINT_FLAGS)

    private var mRadius: Float = 0F
    private var mRectF = RectF()
    private var mContentRect = Rect()
    private var mShowRect = Rect()
    private val mOptions = BitmapFactory.Options()

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null, 0)

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        CIRCLE_CROP_BITMAP_PAINT.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mRadius = (w.toFloat() / 2).coerceAtMost(h.toFloat() / 2)

        mRectF.run {
            left = 0F
            top = 0F
            right = w.toFloat()
            bottom = h.toFloat()
        }

        if (mContentBitmap == null || (mContentBitmap?.width ?: 0 > w && mContentBitmap?.height ?: 0 > h)) {
            mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888
            mOptions.inJustDecodeBounds = true
            mContentBitmap = BitmapFactory.decodeResource(resources, R.mipmap.yileina, mOptions)

            mOptions.inSampleSize =
                (mOptions.outWidth.toFloat() / w).coerceAtMost(mOptions.outHeight.toFloat() / h)
                    .toInt()

            mOptions.inJustDecodeBounds = false
            mContentBitmap = BitmapFactory.decodeResource(resources, R.mipmap.yileina, mOptions)

            mContentRect.run {
                left = 0
                top = 0
                right = mContentBitmap?.width ?: 0
                bottom = mContentBitmap?.height ?: 0
            }
        }

        if (mShowBitmap == null || (mShowBitmap?.width ?: 0 > w && mShowBitmap?.height ?: 0 > h)) {
            mShowBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

            mShowRect.run {
                left = 0
                top = 0
                right = mShowBitmap?.width ?: 0
                bottom = mShowBitmap?.height ?: 0
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        // Draw a circle
        mShowBitmap?.setHasAlpha(true)
        mCanvas.run {
            setBitmap(mShowBitmap)

            drawCircle(
                width.toFloat() / 2,
                height.toFloat() / 2,
                mRadius,
                CIRCLE_CROP_SHAPE_PAINT
            )

            drawBitmap(
                mContentBitmap!!,
                mContentRect,
                mRectF,
                CIRCLE_CROP_BITMAP_PAINT
            )

            setBitmap(null)
        }

        canvas?.drawBitmap(
            mShowBitmap!!,
            mShowRect,
            mRectF,
            CIRCLE_CROP_SHAPE_PAINT
        )
    }
}
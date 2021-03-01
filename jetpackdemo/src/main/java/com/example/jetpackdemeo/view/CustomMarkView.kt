package com.example.jetpackdemeo.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.jetpackdemeo.R

class CustomMarkView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    companion object {
        val TAG = CustomMarkView::class.simpleName
    }

    private val mTextPaint = Paint()
    private val mBackgroundPaint = Paint()
    private val mTextRect = Rect()
    private var mText: String? = ""
    private var mTextColor = Color.BLACK
    private var mTextSize = context.resources.displayMetrics.density * 12 + 0.5F
    private var mBackgroundColor = Color.WHITE

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)


    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMarkView)

        mText = typedArray.getString(R.styleable.CustomMarkView_text)
        mTextColor = typedArray.getColor(R.styleable.CustomMarkView_textColor, Color.WHITE)
        mTextSize = typedArray.getFloat(
            R.styleable.CustomMarkView_textSize,
            12F
        ) * context.resources.displayMetrics.density + 0.5F

        mBackgroundColor =
            typedArray.getColor(R.styleable.CustomMarkView_backgroundColor, Color.BLACK)

        typedArray.recycle()

        mBackgroundPaint.color = mBackgroundColor

        mTextPaint.color = mTextColor
        mTextPaint.textSize = mTextSize
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var width = 0

        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        var height = 0

        mTextPaint.getTextBounds(mText, 0, mText?.length ?: 0, mTextRect)

        when (widthSpecMode) {
            MeasureSpec.UNSPECIFIED -> width = 0
            // wrap_content
            MeasureSpec.AT_MOST -> width = (mTextRect.width() * 1.2F).toInt()
            // 设置确切的值
            MeasureSpec.EXACTLY -> width = widthSpecSize
        }

        when (heightSpecMode) {
            MeasureSpec.UNSPECIFIED -> height = 0
            // wrap_content
            MeasureSpec.AT_MOST -> height = (mTextRect.height() * 1.2F).toInt()
            // 设置确切的值
            MeasureSpec.EXACTLY -> height = heightSpecSize
        }

        if (width == 0 && height == 0) {
            val rect = Rect()
            mTextPaint.getTextBounds("自定义View1", 0, "自定义View1".length, rect)
            height = rect.height()
        }

        if (height >= width) {
            width = (height * 1.5).toInt()
        }

        setMeasuredDimension(width, height)

        Log.e(TAG, "onMeasure(width = $width, height = $height)")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRoundRect(
            RectF(0.toFloat(), 0.toFloat(), width.toFloat(), height.toFloat()),
            height.toFloat() / 2,
            height.toFloat() / 2,
            mBackgroundPaint
        )

        canvas?.drawText(
            mText ?: "",
            (width - mTextRect.width()) / 2.toFloat(),
            height.toFloat() - (height - mTextRect.height()) / 2.toFloat(),
            mTextPaint
        )
    }
}
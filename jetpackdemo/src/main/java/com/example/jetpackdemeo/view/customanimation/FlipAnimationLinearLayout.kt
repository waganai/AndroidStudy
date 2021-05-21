package com.example.jetpackdemeo.view.customanimation

import android.R.attr
import android.R.attr.centerX
import android.R.attr.centerY
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.LinearLayout
import com.example.jetpackdemeo.R

class FlipAnimationLinearLayout(context: Context, attributes: AttributeSet?, defaultStyle: Int) :
    LinearLayout(context, attributes, defaultStyle) {
    private var showBack = false

    private var mWidth //容器的宽度
            = 0
    private var mHeight //容器的高度
            = 0

    private var mViewsWidth //子元素宽度
            = 0
    private var mViewsHeight //子元素高度
            = 0
    private var mPaddingLeft = 0
    private var mPaddingRight = 0
    private var mPaddingTop = 0
    private var mPaddingBottom = 0
    private var mMarginLeft = 0
    private var mMarginTop = 0
    private var mMarginRight = 0
    private var mMarginBottom = 0

    private var mCenterX = 0
    private var mCenterY: Int = 0
    private var mAnimationF: Rotate3dAnimation? = null
    private var mAnimationB: Rotate3dAnimation? = null

    private var mChildTop = 0
    private var mForeGround: View? = null
    private var mBackground: View? = null

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    constructor(context: Context) : this(context, null, 0)

    init {
        LayoutInflater.from(context).inflate(R.layout.animation_flip_layout, this, true);
        mForeGround = findViewById(R.id.mButton)
        mBackground = findViewById(R.id.buttonText)

        mForeGround?.setOnClickListener {
            iMyClick!!.onMyClick("clicked me")
            flipMe()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        measureChildren(widthMeasureSpec, heightMeasureSpec)

        mCenterX = mForeGround?.measuredWidth ?: 0 / 2
        mCenterY = mForeGround?.measuredHeight ?: 0 / 2

        mWidth = 0
        mHeight = 0
        //margin
        //margin
        mMarginLeft = 0
        mMarginTop = 0
        mMarginRight = 0
        mMarginBottom = 0
        //padding
        //padding
        mPaddingLeft = paddingLeft
        mPaddingTop = paddingTop
        mPaddingRight = paddingRight
        mPaddingBottom = paddingBottom

        val childCount = childCount

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val lp = childView.layoutParams as MarginLayoutParams
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
            mViewsHeight += childView.measuredHeight
            mViewsWidth = mViewsWidth.coerceAtLeast(childView.measuredWidth)
            mMarginLeft = 0.coerceAtLeast(lp.leftMargin) //最大左边距
            mMarginTop += lp.topMargin //上边距之和
            mMarginRight = 0.coerceAtLeast(lp.rightMargin) //最大右边距
            mMarginBottom += lp.bottomMargin //下边距之和
        }

        mWidth = measuredWidth + attr.paddingLeft + attr.paddingRight + mMarginLeft + mMarginRight
        mHeight = measuredHeight + attr.paddingBottom + attr.paddingTop + mMarginTop + mMarginBottom
        setMeasuredDimension(
            measureWidth(widthMeasureSpec, mWidth),
            measureHeight(heightMeasureSpec, mHeight)
        )

        //动画
        mAnimationF = Rotate3dAnimation(0F, 90F, mCenterX.toFloat(), mCenterY.toFloat(), 0F, true)
        mAnimationF?.apply {
            duration = 500 //动画持续时间，默认为0
            fillAfter = true //这个false的话动画完了会复原

            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    if (showBack) {
//                        buttonText.setText("BACK BUTTON")
                        mForeGround?.setBackgroundColor(Color.RED)
                    } else { // 背面朝上
//                        mBackGround?.setText("FRONT BUTTON")
                        mForeGround?.setBackgroundColor(Color.BLUE)
                    }
                    mForeGround?.startAnimation(mAnimationB)
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }

        mAnimationB = Rotate3dAnimation(-90F, 0F, centerX.toFloat(), centerY.toFloat(), 0F, true)
        mAnimationB?.apply {
            duration = 500
            fillAfter = true
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mChildTop = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != GONE) {
                child.layout(0, mChildTop, child.measuredWidth, mChildTop + child.measuredHeight)
                mChildTop += child.measuredHeight
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun measureWidth(measureSpec: Int, viewGroupWidth: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = specSize
            MeasureSpec.AT_MOST ->                 /* 将剩余宽度和所有子View + padding的值进行比较，取小的作为ViewGroup的宽度 */result =
                Math.min(viewGroupWidth, specSize)
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

    private fun measureHeight(measureSpec: Int, viewGroupHeight: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = specSize
            MeasureSpec.AT_MOST ->                 /* 将剩余高度和所有子View + padding的值进行比较，取小的作为ViewGroup的高度 */result =
                Math.min(viewGroupHeight, specSize)
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

    /**
     * 定义接口
     */
    interface IMyClick {
        fun onMyClick(str: String?)
    }

    /**
     * 初始化接口变量
     */
    var iMyClick: IMyClick? = null

    /**
     * 自定义事件监听
     * @param _iMyClick
     */
    fun setOnMyClickListener(_iMyClick: IMyClick?) {
        iMyClick = _iMyClick
    }

    private fun flipMe() {
        // 正面朝上
        showBack = !showBack
        mForeGround?.startAnimation(mAnimationF)
    }
}
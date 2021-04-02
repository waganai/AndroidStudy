package com.example.jetpackdemeo.view.recyclerview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AutoScrollRecyclerView(context: Context, attributeSet: AttributeSet?, defStyle: Int) :
    RecyclerView(context, attributeSet, defStyle) {

    companion object {
        val TAG = AutoScrollRecyclerView::class.simpleName
        const val AUTO_SCROLL_UP = 0
        const val AUTO_SCROLL_DOWN = 1
    }

    private val density = resources.displayMetrics.density
    private var mCanScroll = true
    private var mCurrentChild: View? = null

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun dispatchMessage(msg: Message) {
            super.dispatchMessage(msg)
            when (msg.what) {
                AUTO_SCROLL_UP ->
                    autoScrollVertical(-(5 * density + 0.5F).toInt())
                AUTO_SCROLL_DOWN ->
                    autoScrollVertical((5 * density + 0.5F).toInt())
            }
        }
    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context) : this(context, null, 0)

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val currentX = ev.x.toInt()
        val currentY = ev.y.toInt()

        if (!isInView(currentX, currentY, mCurrentChild)) {
            mCurrentChild = getVisibleViewHolder(currentX, currentY)

            Log.e(
                TAG,
                "dispatchTouchEvent() viewHolder = ${(getViewHolderContent(mCurrentChild))}"
            )
        }

        if (!mCanScroll)
            return super.dispatchTouchEvent(ev)

        ev.apply {
            if (x >= right - 100 * density + 0.5F) {
                if (y <= 100 * density + 0.5F) {
                    mCanScroll = false

                    mHandler.removeCallbacksAndMessages(null)
                    mHandler.sendEmptyMessageDelayed(AUTO_SCROLL_UP, 16)

                    return true
                }

                if (y >= bottom - 100 * density + 0.5F) {
                    mCanScroll = false

                    mHandler.removeCallbacksAndMessages(null)
                    mHandler.sendEmptyMessageDelayed(AUTO_SCROLL_DOWN, 16)

                    return true
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    private fun autoScrollVertical(verticalDistance: Int) {
        scrollBy(0, verticalDistance)

        mCanScroll = true
    }

    private fun getVisibleViewHolder(currentX: Int, currentY: Int): View? {
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (isInView(currentX, currentY, child)) {
                return child
            }
        }

        return null
    }

    private fun getViewHolderContent(view: View?): String {
        return view?.run {
            (getChildViewHolder(this) as SimpleAdapter.SimpleViewHolder).getContent()
        } ?: "empty"
    }

    private fun isInView(currentX: Int, currentY: Int, targetView: View?): Boolean {
        return if (targetView == null) false else targetView.left <= currentX &&
                targetView.top <= currentY &&
                targetView.right >= currentX &&
                targetView.bottom >= currentY
    }
}
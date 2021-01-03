package com.hubiao.base.support.utils

import android.content.Context

class DisplayUtils {
    companion object {
        fun dp2Px(context: Context?, dpValue: Float): Int {
            return context?.let { (dpValue * context.resources.displayMetrics.density + 0.5f).toInt() }
                ?: 0
        }
    }
}
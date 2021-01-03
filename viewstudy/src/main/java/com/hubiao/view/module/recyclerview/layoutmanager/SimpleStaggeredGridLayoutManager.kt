package com.hubiao.view.module.recyclerview.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SimpleStaggeredGridLayoutManager: StaggeredGridLayoutManager{
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(spanCount: Int, orientation: Int) : super(spanCount, orientation)


}
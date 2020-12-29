package com.hubiao.view.widget

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnlyUIFragment : Fragment() {

    companion object {
        fun buildOnlyUIFragment(layoutId: Int): OnlyUIFragment {
            val fragment = OnlyUIFragment()
            fragment.setFragmentContentLayoutId(layoutId)

            return fragment
        }
    }

    private var mFragmentContentLayoutId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (mFragmentContentLayoutId > 0) inflater.inflate(
            mFragmentContentLayoutId,
            container,
            false
        ) else {
            val emptyView = TextView(activity)

            emptyView.width = ViewGroup.LayoutParams.MATCH_PARENT
            emptyView.height = ViewGroup.LayoutParams.MATCH_PARENT

            emptyView.text = "No Content"
            emptyView.setTextSize( TypedValue.COMPLEX_UNIT_DIP, 18F)
            emptyView.gravity = Gravity.CENTER

            return emptyView
        }
    }

    fun setFragmentContentLayoutId(layoutId: Int) {
        mFragmentContentLayoutId = layoutId
    }
}
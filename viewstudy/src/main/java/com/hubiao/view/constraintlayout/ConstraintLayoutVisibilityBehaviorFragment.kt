package com.hubiao.view.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hubiao.view.R

class ConstraintLayoutVisibilityBehaviorFragment : Fragment() {

    companion object {
        fun buildFragment(): ConstraintLayoutVisibilityBehaviorFragment {
            return ConstraintLayoutVisibilityBehaviorFragment()
        }
    }

    private var mVisibilityBtn: TextView? = null
    private var mMarginGoneTv: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_layout_constraintlayout_visibility_behavior,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        mVisibilityBtn = view?.findViewById(R.id.btn_gone_margin)
        mMarginGoneTv = view?.findViewById(R.id.tv2_gone_margin)
    }

    private fun initListener() {
        mVisibilityBtn?.setOnClickListener {
            if (mMarginGoneTv?.visibility == View.VISIBLE) {
                mMarginGoneTv?.visibility = View.GONE
            } else {
                mMarginGoneTv?.visibility = View.VISIBLE
            }
        }
    }
}
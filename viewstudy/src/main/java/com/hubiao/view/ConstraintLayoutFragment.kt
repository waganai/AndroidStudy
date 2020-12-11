package com.hubiao.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ConstraintLayoutFragment : Fragment() {

    private var mRootView: View? = null

    private var mLayout1Btn: Button? = null
    private var mTvTips: TextView? = null

    private var mConstraintLayoutFragmentListener: ConstraintLayoutFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_constraintlayout_layout, container, false)

        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init() {
        mLayout1Btn = mRootView?.findViewById(R.id.btn_relative_position)
        mTvTips = mRootView?.findViewById(R.id.tv_tips)

        mTvTips?.text =
            "使用ConstraintLayout需要注意：\n" +
                    "1.ConstraintLayout 是不支持match_parent属性的，可以使用0dp配合该方向两端的约束实现；\n" +
                    "2.ConstraintLayout 也不支持为负数的margin，可以使用space辅助定位；\n" +
                    "3.ConstraintLayout中想和某一个控件水平或者垂直对齐，可以利用另一个控件的上下两端或者左右两端进行约束"


        mLayout1Btn?.setOnClickListener {
            mConstraintLayoutFragmentListener?.onShowLayout1()
        }
    }

    fun setConstraintLayoutFragmentListener(listener: ConstraintLayoutFragmentListener) {
        mConstraintLayoutFragmentListener = listener
    }

    public interface ConstraintLayoutFragmentListener {
        fun onShowLayout1()
    }
}
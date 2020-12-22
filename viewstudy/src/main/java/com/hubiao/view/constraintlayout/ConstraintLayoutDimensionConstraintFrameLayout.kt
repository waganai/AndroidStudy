package com.hubiao.view.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hubiao.view.R

class ConstraintLayoutDimensionConstraintFrameLayout : Fragment() {

    companion object {
        fun buildFragment(): ConstraintLayoutDimensionConstraintFrameLayout {
            return ConstraintLayoutDimensionConstraintFrameLayout()
        }
    }

    private var mBtnContentLess: TextView? = null
    private var mBtnContentMore: TextView? = null
    private var mTvWrapContent1: TextView? = null

    private var mBtnContentLessConstrained: TextView? = null
    private var mBtnContentMoreConstrained: TextView? = null
    private var mTvWrapContent1Constrained: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_layout_constraintlayout_dimension_constraint,
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
        // 没有被约束
        mBtnContentLess = view?.findViewById(R.id.btn_content_less)
        mBtnContentMore = view?.findViewById(R.id.btn_content_more)

        mTvWrapContent1 = view?.findViewById(R.id.tv1_wrap_content)

        // 有被约束
        mBtnContentLessConstrained = view?.findViewById(R.id.btn_content_less_constrained)
        mBtnContentMoreConstrained = view?.findViewById(R.id.btn_content_more_constrained)

        mTvWrapContent1Constrained = view?.findViewById(R.id.tv1_wrap_content_constrained)
    }

    private fun initListener() {
        mBtnContentLess?.setOnClickListener {
            mTvWrapContent1?.text = "升值加薪迎娶白富美，升值加薪迎娶白富美"
        }

        mBtnContentMore?.setOnClickListener {
            mTvWrapContent1?.text = "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美"
        }

        mBtnContentLessConstrained?.setOnClickListener {
            mTvWrapContent1?.text = "升值加薪迎娶白富美，升值加薪迎娶白富美"
        }

        mBtnContentMoreConstrained?.setOnClickListener {
            mTvWrapContent1Constrained?.text = "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美" +
                    "升值加薪迎娶白富美，升值加薪迎娶白富美， 升值加薪迎娶白富美，升值加薪迎娶白富美"
        }
    }
}
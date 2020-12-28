package com.hubiao.view.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.hubiao.view.R

class ConstraintLayoutVirtualHelpersFragment : Fragment() {

    companion object {
        fun buildFragment(): ConstraintLayoutVirtualHelpersFragment {
            return ConstraintLayoutVirtualHelpersFragment()
        }
    }

    private var mTvShortA: TextView? = null
    private var mTvShortB: TextView? = null

    private var mTvBarrier1: TextView? = null
    private var mTvBarrier2: TextView? = null

    private var mTvShow: TextView? = null
    private var mTvHide: TextView? = null

    private var mTvShow1: TextView? = null
    private var mTvHide1: TextView? = null

    private var mTv1Group: TextView? = null

    private var mGroup: Group? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_layout_constraintlayout_virtual_helpers_objects,
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
        mTvShortA = view?.findViewById(R.id.tv_a_short)
        mTvShortB = view?.findViewById(R.id.tv_b_short)

        mTvBarrier1 = view?.findViewById(R.id.tv1_barrier)
        mTvBarrier2 = view?.findViewById(R.id.tv2_barrier)

        mTvShow = view?.findViewById(R.id.tv_show)
        mTvHide = view?.findViewById(R.id.tv_hide)

        mTvShow1 = view?.findViewById(R.id.tv1_show)
        mTvHide1 = view?.findViewById(R.id.tv1_hide)

        mTv1Group = view?.findViewById(R.id.tv1_group)

        mGroup = view?.findViewById(R.id.group1)
    }

    private fun initListener() {
        mTvShortA?.setOnClickListener {
            mTvBarrier1?.layoutParams?.width = 100
            mTvBarrier2?.layoutParams?.width = 200

            mTvBarrier1?.requestLayout()
            mTvBarrier2?.requestLayout()
        }

        mTvShortB?.setOnClickListener {
            mTvBarrier1?.layoutParams?.width = 200
            mTvBarrier2?.layoutParams?.width = 100

            mTvBarrier1?.requestLayout()
            mTvBarrier2?.requestLayout()
        }

        mTvShow?.setOnClickListener {
            mGroup?.visibility = View.VISIBLE
        }

        mTvHide?.setOnClickListener {
            mGroup?.visibility = View.GONE
        }

        mTvShow1?.setOnClickListener {
            mTv1Group?.visibility = View.VISIBLE
        }

        mTvHide1?.setOnClickListener {
            mTv1Group?.visibility = View.VISIBLE
        }
    }
}
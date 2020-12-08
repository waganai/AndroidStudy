package com.hubiao.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ConstraintLayoutFragment : Fragment() {

    private var mRootView: View? = null

    private var mLayout1Btn: Button? = null

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
        mLayout1Btn = mRootView?.findViewById(R.id.btn_layout1)

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
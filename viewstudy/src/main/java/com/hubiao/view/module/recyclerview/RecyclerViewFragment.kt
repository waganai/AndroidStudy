package com.hubiao.view.module.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.hubiao.view.R

class RecyclerViewFragment : Fragment() {

    private var mRootView: View? = null

    private var mDividerItemDecorationBtn: Button? = null
    private var mSimpleItemDecorationBtn: Button? = null

    private var mRecyclerViewFragmentListener: RecyclerViewFragmentListener? = null

    companion object {
        fun buildFragment(): RecyclerViewFragment {
            return RecyclerViewFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRootView = view

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        mDividerItemDecorationBtn = mRootView?.findViewById(R.id.btn_divider_item_decoration)
        mSimpleItemDecorationBtn = mRootView?.findViewById(R.id.btn_simple_Item_decoration)
    }

    private fun initListener() {
        mDividerItemDecorationBtn?.setOnClickListener {
            mRecyclerViewFragmentListener?.onShowDividerItemDecorationFragment()
        }
        mSimpleItemDecorationBtn?.setOnClickListener {
            mRecyclerViewFragmentListener?.onShowSimpleItemDecorationFragment()
        }
    }

    fun setRecyclerViewFragmentListener(listener: RecyclerViewFragmentListener) {
        mRecyclerViewFragmentListener = listener
    }

    interface RecyclerViewFragmentListener {
        fun onShowDividerItemDecorationFragment()

        fun onShowSimpleItemDecorationFragment()
    }
}
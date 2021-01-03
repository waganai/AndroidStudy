package com.hubiao.view.module.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class StaggeredGridLayoutManagerRecyclerViewFragment : Fragment() {

    companion object {
        val TAG = StaggeredGridLayoutManagerRecyclerViewFragment::class.java.simpleName
    }

    private var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
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

    }

    private fun initListener() {

    }
}
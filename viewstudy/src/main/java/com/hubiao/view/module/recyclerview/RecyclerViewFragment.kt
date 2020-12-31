package com.hubiao.view.module.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hubiao.view.R

class RecyclerViewFragment : Fragment() {

    private var mRootView: View? = null

    private var mSimpleRecyclerView: RecyclerView? = null

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
        mRootView = view

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        activity?.let {
            mSimpleRecyclerView = mRootView?.findViewById(R.id.simple_recyclerView)

            mSimpleRecyclerView?.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            // DividerItemDecoration设置的分割线是透明的
            // 顶部没有分割线，底部有
            mSimpleRecyclerView?.addItemDecoration(
                DividerItemDecoration(
                    it,
                    DividerItemDecoration.VERTICAL
                )
            )

            val simpleRecyclerViewAdapter = SimpleRecyclerViewAdapter(it)

            mSimpleRecyclerView?.adapter = simpleRecyclerViewAdapter

            val dataList = listOf(
                "AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ",
                "KKK", "LLL", "MMM", "NNN", "OOO", "PPP", "QQQ", "RRR", "SSS", "TTT",
                "UUU", "VVV", "WWW", "XXX", "YYY", "ZZZ"
            )

            simpleRecyclerViewAdapter.setData(dataList)
            simpleRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun initListener() {

    }
}
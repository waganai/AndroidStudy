package com.hubiao.view.module.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hubiao.base.support.utils.DisplayUtils
import com.hubiao.view.R
import com.hubiao.view.module.recyclerview.adapter.SimpleRecyclerViewAdapter
import com.hubiao.view.module.recyclerview.itemdecoration.SimpleItemDecoration

class SimpleItemDecorationRecyclerViewFragment : Fragment() {

    companion object {
        val TAG = SimpleItemDecorationRecyclerViewFragment::class.java.simpleName

        fun buildFragment(): SimpleItemDecorationRecyclerViewFragment {
            return SimpleItemDecorationRecyclerViewFragment()
        }
    }

    private var mRootView: View? = null

    private var mRecyclerView1: RecyclerView? = null
    private var mRecyclerView2: RecyclerView? = null
    private var mRecyclerView3: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_simple_itemdecoration_recyclerview,
            container,
            false
        )
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
        mRecyclerView1 = mRootView?.findViewById(R.id.recyclerview1_simple_item_decoration)
        mRecyclerView2 = mRootView?.findViewById(R.id.recyclerview2_simple_item_decoration)
        mRecyclerView3 = mRootView?.findViewById(R.id.recyclerview3_simple_item_decoration)

        mRecyclerView1?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        mRecyclerView1?.addItemDecoration(
            SimpleItemDecoration
                .buildItemDecoration()
                .setOrientation(LinearLayoutManager.HORIZONTAL)
                .setDividerColor(Color.RED)
                .setDividerSize(DisplayUtils.dp2Px(activity, 10F))
                .setDrawForLastItem(true)
        )

        val listData1 =
            listOf(
                "233", "233", "233", "233", "233", "233", "233", "233", "233", "233",
                "233", "233", "233", "233", "233", "233", "233", "233", "233", "233",
                "233", "233", "233", "233", "233", "233", "233", "233", "233", "233"
            )

        activity?.let {
            val adapter1 = SimpleRecyclerViewAdapter(it)
            adapter1.setData(listData1)

            mRecyclerView1?.adapter = adapter1
        }

        mRecyclerView2?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecyclerView2?.addItemDecoration(
            SimpleItemDecoration.buildItemDecoration()
                .setOrientation(LinearLayoutManager.VERTICAL)
                .setDividerColor(Color.GREEN)
                .setDividerSize(DisplayUtils.dp2Px(activity, 5F))
                .setDrawForLastItem(false)
        )

        val listData2 =
            listOf(
                "666", "666", "666", "666", "666", "666", "666", "666", "666", "666",
                "666", "666", "666", "666", "666", "666", "666", "666", "666", "666",
                "666", "666", "666", "666", "666", "666", "666", "666", "666", "666"
            )

        activity?.let {
            val adapter2 = SimpleRecyclerViewAdapter(it)
            adapter2.setData(listData2)

            mRecyclerView2?.adapter = adapter2
        }

        mRecyclerView3?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        mRecyclerView3?.addItemDecoration(
            SimpleItemDecoration.buildItemDecoration()
                .setDrawOver(true)
                .setDrawOverColor(Color.YELLOW)
        )

        val listData3 = listOf(
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android",
            "Android"
        )

        activity?.let {
            val adapter3 = SimpleRecyclerViewAdapter(it)
            adapter3.setData(listData3)

            mRecyclerView3?.adapter = adapter3
        }

    }

    private fun initListener() {

    }
}
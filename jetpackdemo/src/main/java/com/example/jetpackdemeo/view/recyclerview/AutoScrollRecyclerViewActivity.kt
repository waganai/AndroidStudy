package com.example.jetpackdemeo.view.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemeo.databinding.ActivityAutoScrollrecyclerviewLayoutBinding

class AutoScrollRecyclerViewActivity : AppCompatActivity() {

    companion object{
        val TAG = AutoScrollRecyclerViewActivity::class.simpleName
    }

    private var mBinding: ActivityAutoScrollrecyclerviewLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAutoScrollrecyclerviewLayoutBinding.inflate(layoutInflater)

        setContentView(mBinding?.root)

        init()
    }

    fun init() {
        val contentList = listOf(
            "AAA1",
            "AAA2",
            "AAA3",
            "AAA4",
            "AAA5",
            "AAA6",
            "AAA7",
            "AAA8",
            "AAA9",
            "AAA10",
            "AAA11",
            "AAA12",
            "AAA13",
            "AAA14",
            "AAA15",
            "AAA16",
            "AAA17",
            "AAA18",
            "AAA19",
            "AAA20",
            "AAA21",
            "AAA22",
            "AAA23",
            "AAA24",
            "AAA25",
            "AAA26",
            "AAA27",
            "AAA28",
            "AAA29",
            "AAA30"
        )

        mBinding?.rvContent?.apply {
            val adapter = SimpleAdapter(this@AutoScrollRecyclerViewActivity)
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(
                this@AutoScrollRecyclerViewActivity,
                RecyclerView.VERTICAL,
                false
            )

            adapter.setContentList(contentList)
        }
    }

}
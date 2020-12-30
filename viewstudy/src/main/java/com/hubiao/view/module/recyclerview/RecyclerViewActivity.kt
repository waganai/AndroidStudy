package com.hubiao.view.module.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.R

class RecyclerViewActivity : AppCompatActivity() {

    private var mRecyclerViewFragment: RecyclerViewFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recyclerview_layout)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        mRecyclerViewFragment = RecyclerViewFragment.buildFragment()

        mRecyclerViewFragment?.let {
            val fragmentTransient = supportFragmentManager.beginTransaction()
            fragmentTransient.replace(
                R.id.fl_container,
                it,
                RecyclerViewFragment::class.java.simpleName
            )
            fragmentTransient.commit()
        }
    }

    private fun initListener() {

    }

}
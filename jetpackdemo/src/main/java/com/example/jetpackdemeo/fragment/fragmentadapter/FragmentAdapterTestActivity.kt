package com.example.jetpackdemeo.fragment.fragmentadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.ActivityFragmentViewpagerTestLayoutBinding
import com.example.jetpackdemeo.fragment.LogFragment

class FragmentAdapterTestActivity : AppCompatActivity() {

    private var viewBinding: ActivityFragmentViewpagerTestLayoutBinding? = null
    private val mFragments: MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityFragmentViewpagerTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initView()
    }

    private fun initView() {
        for (i in 0..3) {
            mFragments.add(LogFragment.createInstance("LogFragment-$i"))
        }

        viewBinding?.vpFragment?.adapter = FPAdapter(supportFragmentManager, mFragments)
    }
}
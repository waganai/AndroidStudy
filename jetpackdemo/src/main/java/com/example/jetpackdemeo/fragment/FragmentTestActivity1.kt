package com.example.jetpackdemeo.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.ActivityFragmentTest1LayoutBinding

class FragmentTestActivity1 : AppCompatActivity() {

    private var viewBinding: ActivityFragmentTest1LayoutBinding? = null
    private val mFragments: MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityFragmentTest1LayoutBinding.inflate(layoutInflater)

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
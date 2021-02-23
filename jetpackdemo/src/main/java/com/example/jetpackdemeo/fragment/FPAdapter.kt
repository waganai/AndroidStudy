package com.example.jetpackdemeo.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FPAdapter(fm: FragmentManager, fragments: MutableList<Fragment>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var mFragments: MutableList<Fragment> = mutableListOf()

    init {
        mFragments = fragments
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mFragments.size
}
package com.example.jetpackdemeo.view.cordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentCoordinatorlayoutTest2LayoutBinding

class CoordinatorLayoutTestFragment2: Fragment() {

    companion object{
        private val TAG = CoordinatorLayoutTestFragment2::class.simpleName
    }

    private val viewBinding by lazy {   FragmentCoordinatorlayoutTest2LayoutBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

    }
}
package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.activity.LifeCycleTestActivity1
import com.example.jetpackdemeo.databinding.FragmentPageDemoLayoutBinding
import com.example.jetpackdemeo.fragment.FragmentTestActivity

class PageDemoFragment() : Fragment() {

    companion object {
        val TAG = PageDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): PageDemoFragment {
            return PageDemoFragment()
        }
    }

    private var viewBinding: FragmentPageDemoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPageDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnActivity.setOnClickListener {
                startActivity(Intent(activity, LifeCycleTestActivity1::class.java))
            }

            btnFragment.setOnClickListener {
                startActivity(Intent(activity, FragmentTestActivity::class.java))
            }
        }
    }
}
package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentThreadDemoLayoutBinding
import com.example.jetpackdemeo.multithreading.MultithreadingTestActivity

class ThreadDemoFragment : Fragment() {
    companion object {
        val TAG = ThreadDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): ThreadDemoFragment {
            return ThreadDemoFragment()
        }
    }

    private var viewBinding: FragmentThreadDemoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentThreadDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnThread.setOnClickListener {
                startActivity(Intent(activity, MultithreadingTestActivity::class.java))
            }
        }
    }
}
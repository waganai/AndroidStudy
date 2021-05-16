package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentTestDemoLayoutBinding
import com.example.jetpackdemeo.test.BloomFilterTestActivity

class TestDemoFragment : Fragment() {
    companion object {
        val TAG = TestDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): TestDemoFragment {
            return TestDemoFragment()
        }
    }

    private var viewBinding: FragmentTestDemoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTestDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnBloomfilter.setOnClickListener {
                startActivity(Intent(activity, BloomFilterTestActivity::class.java))
            }
        }
    }
}
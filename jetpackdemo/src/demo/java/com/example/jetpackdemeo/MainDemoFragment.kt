package com.example.jetpackdemeo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentMainDemoLayoutBinding

class MainDemoFragment() : Fragment() {

    companion object {
        const val PAGE_INDEX = 1
        const val VIEW_INDEX = 2
        const val THIRDPARTY_INDEX = 3
        const val THREAD_INDEX = 4
        const val PROCESS_INDEX = 5
        const val TEST_INDEX = 6
        
        private val TAG = MainDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): MainDemoFragment {
            return MainDemoFragment()
        }
    }

    private var viewBinding: FragmentMainDemoLayoutBinding? = null
    private var mSwitchListener: SwitchMainDemoFragmentPageListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainDemoLayoutBinding.inflate(inflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnPageDemo.setOnClickListener {
                Log.e(TAG, "init() mSwitchListener = $mSwitchListener")
                mSwitchListener?.onSwitchFragmentPage(PAGE_INDEX)
            }

            btnViewDemo.setOnClickListener {
                mSwitchListener?.onSwitchFragmentPage(VIEW_INDEX)
            }

            btnThirdpartyDemo.setOnClickListener {
                mSwitchListener?.onSwitchFragmentPage(THIRDPARTY_INDEX)
            }

            btnThreadDemo.setOnClickListener {
                mSwitchListener?.onSwitchFragmentPage(THREAD_INDEX)
            }

            btnProcessDemo.setOnClickListener {
                mSwitchListener?.onSwitchFragmentPage(PROCESS_INDEX)
            }

            btnTestDemo.setOnClickListener {
                mSwitchListener?.onSwitchFragmentPage(TEST_INDEX)
            }
        }
    }

    fun setSwitchListener(listener: SwitchMainDemoFragmentPageListener): MainDemoFragment {
        mSwitchListener = listener

        return this@MainDemoFragment
    }

    public interface SwitchMainDemoFragmentPageListener {
        fun onSwitchFragmentPage(page: Int)
    }
}
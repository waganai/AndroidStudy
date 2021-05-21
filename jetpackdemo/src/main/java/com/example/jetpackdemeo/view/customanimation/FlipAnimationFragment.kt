package com.example.jetpackdemeo.view.customanimation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentFlipAnimationLayoutBinding

class FlipAnimationFragment : Fragment() {
    companion object {
        private val TAG = FlipAnimationFragment::class.simpleName
    }

    private var mViewBinding: FragmentFlipAnimationLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentFlipAnimationLayoutBinding.inflate(layoutInflater)
        return mViewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        mViewBinding?.apply {
            llFlipAnimation.setOnMyClickListener(object : FlipAnimationLinearLayout.IMyClick {
                override fun onMyClick(str: String?) {
                    Log.e(TAG, "$str")
                }
            })
        }
    }
}
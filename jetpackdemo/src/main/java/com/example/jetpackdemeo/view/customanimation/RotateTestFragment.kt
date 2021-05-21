package com.example.jetpackdemeo.view.customanimation

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentRoateAnimationLayoutBinding

class RotateTestFragment : Fragment() {

    companion object {
        private val TAG = RotateTestFragment::class.simpleName
    }

    private var mViewBinding: FragmentRoateAnimationLayoutBinding? = null
    private var mAnimation1: Rotate3dAnimation? = null
    private var mAnimation2: Rotate3dAnimation? = null

    private var mIsBack = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentRoateAnimationLayoutBinding.inflate(layoutInflater)

        return mViewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mViewBinding?.apply {
            tvRoate.post {
                tvRoate.apply {
                    tvRoate.setBackgroundColor(Color.BLUE)

                    setOnClickListener {
                        mIsBack = !mIsBack

                        tvRoate.startAnimation(mAnimation1)
                    }
                }

                //动画
                mAnimation1 = Rotate3dAnimation(
                    0F,
                    90F,
                    tvRoate.width.toFloat() / 2,
                    tvRoate.height.toFloat()/2,
                    0F,
                    true
                )
                mAnimation1?.apply {
                    duration = 500 //动画持续时间，默认为0
                    fillAfter = true //这个false的话动画完了会复原

                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {
                            if (mIsBack) {
                                tvRoate.setBackgroundColor(Color.RED)
                            } else { // 背面朝上
                                tvRoate.setBackgroundColor(Color.BLUE)
                            }
                            tvRoate.startAnimation(mAnimation2)
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                }

                mAnimation2 = Rotate3dAnimation(
                    -90F,
                    0F,
                    tvRoate.width.toFloat() / 2,
                    tvRoate.height.toFloat()/2,
                    0F,
                    true
                )
                mAnimation2?.apply {
                    duration = 500
                    fillAfter = true
                }
            }
        }
    }
}
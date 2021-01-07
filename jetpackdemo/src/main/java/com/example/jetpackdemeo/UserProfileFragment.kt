package com.example.jetpackdemeo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentUserProfileLayoutBinding

class UserProfileFragment : Fragment() {

    lateinit var viewBinding: FragmentUserProfileLayoutBinding
    var textColor = Color.WHITE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentUserProfileLayoutBinding.inflate(inflater, container, false)

//        return super.onCreateView(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        viewBinding.btnUser.text = "UserProFile"
    }

    private fun initListener() {
        viewBinding.btnUser.apply {
            setOnClickListener {
                textColor = if (textColor == Color.WHITE) {
                    Color.RED
                } else {
                    Color.WHITE
                }

                viewBinding.btnUser.setTextColor(textColor)
            }
        }
    }
}
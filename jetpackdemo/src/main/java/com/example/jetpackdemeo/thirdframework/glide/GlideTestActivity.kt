package com.example.jetpackdemeo.thirdframework.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jetpackdemeo.databinding.ActivityGlideTestLayoutBinding

class GlideTestActivity : AppCompatActivity() {

    var viewBinding: ActivityGlideTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityGlideTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initView()
    }

    private fun initView() {
        viewBinding?.apply {
            Glide
                .with(this@GlideTestActivity)
                .load("https://b0.bdstatic.com/comment/AbD06ZlrNmmzmXl7Cw5uFA957ac5114bcf7a68d6bd51e8493dfded.jpg@w_967,h_967")
                .into(ivGlide1)
        }
    }
}
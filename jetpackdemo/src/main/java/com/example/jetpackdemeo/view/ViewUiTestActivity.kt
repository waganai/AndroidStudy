package com.example.jetpackdemeo.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityViewuiTestLayoutBinding

class ViewUiTestActivity : AppCompatActivity() {

    var viewBinding: ActivityViewuiTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityViewuiTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initListener()
    }

    private fun initListener() {
        viewBinding?.apply {
            btnCustommarkview.setOnClickListener {
                startActivity(Intent(this@ViewUiTestActivity, ViewTestActivity::class.java))
            }
        }
    }
}
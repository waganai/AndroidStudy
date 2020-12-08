package com.hubiao.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout_layout)
    }

    fun startConstraintLayoutActivity(view: View) {
        startActivity(Intent(this@LayoutActivity, ConstraintLayoutActivity::class.java))
    }
}
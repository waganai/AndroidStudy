package com.hubiao.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.module.cardview.CardViewActivity
import com.hubiao.view.module.constraintlayout.ConstraintLayoutActivity
import com.hubiao.view.module.recyclerview.RecyclerViewActivity

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout_layout)
    }

    fun startConstraintLayoutActivity(view: View) {
        startActivity(Intent(this@LayoutActivity, ConstraintLayoutActivity::class.java))
    }

    fun startCardViewActivity(view: View) {
        startActivity(Intent(this@LayoutActivity, CardViewActivity::class.java))
    }

    fun startRecyclerViewActivity(view: View) {
        startActivity(Intent(this@LayoutActivity, RecyclerViewActivity::class.java))
    }
}
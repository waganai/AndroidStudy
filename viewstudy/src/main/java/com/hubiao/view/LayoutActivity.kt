package com.hubiao.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.hubiao.base.LAYOUT_ACTIVITY
import com.hubiao.view.module.cardview.CardViewActivity
import com.hubiao.view.module.constraintlayout.ConstraintLayoutActivity
import com.hubiao.view.module.recyclerview.RecyclerViewActivity
import com.hubiao.view.module.toast.ToastTestActivity

@Route(path = LAYOUT_ACTIVITY)
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

    fun startToastActivity(view: View) {
        startActivity(Intent(this@LayoutActivity, ToastTestActivity::class.java))
    }
}
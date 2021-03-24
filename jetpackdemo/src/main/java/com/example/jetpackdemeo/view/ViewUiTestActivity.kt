package com.example.jetpackdemeo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityViewuiTestLayoutBinding

class ViewUiTestActivity : AppCompatActivity() {

    companion object {
        val VIEW_ACTIVITY_TYPE = "VIEW_ACTIVITY_TYPE"

        val MARK_VIEW_VIEWGROP = 0;
        val BITMAP_SHADER = 1
    }

    var viewBinding: ActivityViewuiTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityViewuiTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)
    }

    fun onStartMarkViewActivity(view: View) {
        startActivity(
            Intent(
                this@ViewUiTestActivity,
                ViewTestActivity::class.java
            ).putExtra(VIEW_ACTIVITY_TYPE, MARK_VIEW_VIEWGROP)
        )
    }

    fun onStartBitmapShaderActivity(view: View) {
        startActivity(
            Intent(
                this@ViewUiTestActivity,
                ViewTestActivity::class.java
            ).putExtra(VIEW_ACTIVITY_TYPE, BITMAP_SHADER)
        )
    }
}
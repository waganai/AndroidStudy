package com.example.jetpackdemeo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityViewuiTestLayoutBinding

class ViewUiTestActivity : AppCompatActivity() {

    companion object {
        const val VIEW_ACTIVITY_TYPE = "VIEW_ACTIVITY_TYPE"

        const val MARK_VIEW_VIEWGROUP = 0;
        const val BITMAP_SHADER = 1
        const val ROUND_VIEW_1 = 2
        const val ROUND_VIEW_2 = 3
        const val ROUND_VIEW_3 = 4
        const val ROUND_VIEW_4 = 5
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
            ).putExtra(VIEW_ACTIVITY_TYPE, MARK_VIEW_VIEWGROUP)
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
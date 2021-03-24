package com.example.jetpackdemeo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityBitmapshaderLayoutBinding
import com.example.jetpackdemeo.databinding.ActivityMarkviewViewgroupLayoutBinding

class ViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (intent.getIntExtra(
            ViewUiTestActivity.VIEW_ACTIVITY_TYPE,
            ViewUiTestActivity.MARK_VIEW_VIEWGROP
        )) {
            ViewUiTestActivity.MARK_VIEW_VIEWGROP ->
                setContentView(ActivityMarkviewViewgroupLayoutBinding.inflate(layoutInflater).root)

            ViewUiTestActivity.BITMAP_SHADER ->
                setContentView(ActivityBitmapshaderLayoutBinding.inflate(layoutInflater).root)
        }
    }
}
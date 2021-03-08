package com.example.jetpackdemeo.thirdframework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityThirdframeworkLayoutBinding

class ThirdFrameworkTestActivity : AppCompatActivity() {

    var viewBindings: ActivityThirdframeworkLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityThirdframeworkLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.apply {
            btnGlide.setOnClickListener {
                startActivity(
                    Intent(
                        this@ThirdFrameworkTestActivity,
                        GlideTestActivity::class.java
                    )
                )
            }
        }
    }
}
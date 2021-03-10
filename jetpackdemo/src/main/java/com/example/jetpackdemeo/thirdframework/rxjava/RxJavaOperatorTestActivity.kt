package com.example.jetpackdemeo.thirdframework.rxjava

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaOperatorLayoutBinding

class RxJavaOperatorTestActivity : AppCompatActivity() {

    var viewBinding: ActivityRxjavaOperatorLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityRxjavaOperatorLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnCreate.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaCreateOperatorActivity::class.java
                    )
                )
            }

            btnTransform.setOnClickListener { }

            btnCompanion.setOnClickListener { }

            btnFunction.setOnClickListener { }

            btnBool.setOnClickListener { }
        }
    }
}
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

            btnTransform.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaTransformOperatorActivity::class.java
                    )
                )
            }

            btnCombine.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaCombineOperatorActivity::class.java
                    )
                )
            }

            btnFunction.setOnClickListener {
                startActivity(Intent(this@RxJavaOperatorTestActivity,
                RxJavaFunctionOperatorTestActivity::class.java))
            }

            btnBool.setOnClickListener { }
        }
    }
}
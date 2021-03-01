package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.activity.LifeCycleTestActivity1
import com.example.jetpackdemeo.context.ContextTestActivity
import com.example.jetpackdemeo.databinding.ActivityJetpackDemoLayoutBinding
import com.example.jetpackdemeo.fragment.FragmentTestActivity
import com.example.jetpackdemeo.view.ViewTestActivity

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJetpackDemoLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJetpackDemoLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        binding.tvTitleJetpackDemo.text = "JetPack Demo"
        binding.btnActivityJetpackDemo.text = "Start JetPack Activity"
    }

    private fun initListener() {
        binding.apply {
            btnStartActivity.setOnClickListener {
                startActivity(Intent(this@DemoActivity, LifeCycleTestActivity1::class.java))
            }

            btnStartFragment.setOnClickListener {
                startActivity(Intent(this@DemoActivity, FragmentTestActivity::class.java))
            }

            btnStartContext.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ContextTestActivity::class.java))
            }

            btnStartView.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ViewTestActivity::class.java))
            }

            btnActivityJetpackDemo.setOnClickListener {
                startActivity(Intent(this@DemoActivity, JetPackActivity::class.java))
            }
        }
    }
}
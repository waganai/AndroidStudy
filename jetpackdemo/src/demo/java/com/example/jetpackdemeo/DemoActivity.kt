package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityJetpackDemoLayoutBinding

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
        binding.btnActivityJetpackDemo.setOnClickListener {
            startActivity(Intent(this, JetPackActivity::class.java))
        }
    }
}
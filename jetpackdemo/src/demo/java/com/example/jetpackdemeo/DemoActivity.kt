package com.example.jetpackdemeo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityJetpackDemoLayoutBinding
import java.util.*

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
            Toast.makeText(this, "点击事件", Toast.LENGTH_LONG).show()

            binding.tvTitleJetpackDemo.text = "JetPack Demo : " + Date(System.currentTimeMillis()).toString()
        }
    }
}
package com.example.jetpackdemeo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityJetpackLayoutBinding

class JetPackActivity : AppCompatActivity() {

    lateinit var mViewBinding: ActivityJetpackLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = ActivityJetpackLayoutBinding.inflate(layoutInflater)

        setContentView(mViewBinding.root)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                mViewBinding.flContainer.id,
                ViewModelFragment(),
                ViewModelFragment::class.java.simpleName
            ).commit()
    }

    private fun initListener() {

    }
}
package com.example.jetpackdemeo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityFragmentTestLayoutBinding
import com.example.jetpackdemeo.fragment.fragmentadapter.FragmentAdapterTestActivity
import com.example.jetpackdemeo.fragment.fragmentstateadapter.FragmentStateAdapterTestActivity

class FragmentTestActivity : AppCompatActivity() {

    var viewBinding: ActivityFragmentTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityFragmentTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initListener()
    }

    private fun initListener() {
        viewBinding?.apply {
            btnFragmentFragmentadapterActivity.setOnClickListener {
                startActivity(
                    Intent(
                        this@FragmentTestActivity,
                        FragmentAdapterTestActivity::class.java
                    )
                )
            }

            btnFragmentFragmentstateadapterActivity.setOnClickListener {
                startActivity(
                    Intent(
                        this@FragmentTestActivity,
                        FragmentStateAdapterTestActivity::class.java
                    )
                )
            }

            btnFragmentRemoveAddActivity.setOnClickListener {
                startActivity(
                    Intent(
                        this@FragmentTestActivity,
                        FragmentAddRemoveTestActivity::class.java
                    )
                )
            }
        }
    }
}
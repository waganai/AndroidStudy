package com.example.jetpackdemeo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityShowLayoutBinding

class ShowActivity : AppCompatActivity() {

    var viewBinding: ActivityShowLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityShowLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)
    }
}
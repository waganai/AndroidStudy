package com.example.jetpackdemeo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityViewtestLayoutBinding

class ViewTestActivity:AppCompatActivity() {

    var viewBindings :ActivityViewtestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityViewtestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)
    }

}
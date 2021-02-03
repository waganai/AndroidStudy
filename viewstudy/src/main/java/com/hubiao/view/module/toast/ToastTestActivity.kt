package com.hubiao.view.module.toast

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.R

class ToastTestActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_toast_test_layout)

        findViewById<View>(R.id.btn_short_toast).setOnClickListener {
            Toast.makeText(this, "Short Toast", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btn_long_toast).setOnClickListener {
            Toast.makeText(this, "Long Toast", Toast.LENGTH_LONG).show()
        }

        findViewById<View>(R.id.btn_10ms_toast).setOnClickListener {
            Toast.makeText(this, "10 millis second Toast", 10).show()
        }

        findViewById<View>(R.id.btn_10s_toast).setOnClickListener {
            Toast.makeText(this, "10 second Toast", 100000).show()
        }

        findViewById<View>(R.id.btn_negative_1s_toast).setOnClickListener {
            Toast.makeText(this, "Negative  1 second Toast", -1000).show()
        }
    }

    override fun onResume() {
        super.onResume()
    }

}
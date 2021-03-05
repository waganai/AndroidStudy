package com.example.jetpackdemeo.toast

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityToastLayoutBinding
import com.example.jetpackdemeo.databinding.ToastCustomLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

class ToastTestActivity : AppCompatActivity() {

    companion object{
        val TAG = ToastTestActivity::class.simpleName
    }

    var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    var viewBinding: ActivityToastLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityToastLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnShow50Toast.setOnClickListener {
                for (i in 1..50) {
                    Log.e(TAG, "Toast.$i")
                    Toast.makeText(this@ToastTestActivity, "Toast: $i", Toast.LENGTH_SHORT).show()
                }
            }

            btnCustomToast.setOnClickListener {
                val customToast = Toast(applicationContext)

                val viewBindingCustomToastLayout = ToastCustomLayoutBinding.inflate(layoutInflater)
                viewBindingCustomToastLayout.apply {
                    val date = Date()
                    date.time = System.currentTimeMillis()

                    tvCustomToastContent.text = "自定义Toast: ${format.format(date)}"
                }

                customToast.view = viewBindingCustomToastLayout.root
                customToast.setGravity(Gravity.CENTER, 0, 0)
                customToast.duration = Toast.LENGTH_SHORT

                customToast.show()
            }
        }
    }
}
package com.example.jetpackdemeo.thirdframework.okhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityOkhttpLayoutBinding
import okhttp3.*
import java.io.IOException

class OkHttpTestActivity : AppCompatActivity() {

    companion object {
        val TAG = OkHttpTestActivity::class.simpleName
    }

    var viewBindings: ActivityOkhttpLayoutBinding? = null

    private val client: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityOkhttpLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.apply {
            btnOkhttpTest1.setOnClickListener {
                val request = Request.Builder().url("https://www.baidu.com").get().build()
                val call = client.newCall(request)
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(TAG, "onFailure() $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.e(TAG, "onResponse() code = ${response.code()}, message = ${response.message()} ")
                    }
                })
            }
        }
    }
}
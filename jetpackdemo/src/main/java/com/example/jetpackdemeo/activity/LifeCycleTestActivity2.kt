package com.example.jetpackdemeo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityLifecycleTest2LayoutBinding

class LifeCycleTestActivity2 : AppCompatActivity() {

    var viewBinding: ActivityLifecycleTest2LayoutBinding? = null

    companion object {
        val TAG = LifeCycleTestActivity2::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate()")

        viewBinding = ActivityLifecycleTest2LayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.e(TAG, "onRestoreInstanceState()")
    }

    override fun onRestart() {
        super.onRestart()

        Log.e(TAG, "onRestart()")
    }

    override fun onStart() {
        super.onStart()

        Log.e(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.e(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.e(TAG, "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.e(TAG, "onSaveInstanceState()")
    }

    override fun onStop() {
        super.onStop()

        Log.e(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(TAG, "onDestroy()")
    }

    private fun initListener() {
        viewBinding?.btnStartOther?.setOnClickListener {
            startActivity(Intent(this@LifeCycleTestActivity2, LifeCycleTestActivity1::class.java))
        }
    }
}
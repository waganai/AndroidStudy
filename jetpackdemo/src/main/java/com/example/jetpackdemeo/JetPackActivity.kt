package com.example.jetpackdemeo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.jetpackdemeo.databinding.ActivityJetpackLayoutBinding

class JetPackActivity : AppCompatActivity() {

    lateinit var mViewBinding: ActivityJetpackLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(JetPackActivityLifeCycleObserver())

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

    private class JetPackActivityLifeCycleObserver : LifecycleObserver {
        companion object {
            private val TAG = JetPackActivityLifeCycleObserver::class.java.simpleName
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            Log.e(TAG, "onCreate()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            Log.e(TAG, "onStart()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            Log.e(TAG, "onResume()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            Log.e(TAG, "onPause()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            Log.e(TAG, "onStop()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            Log.e(TAG, "onDestroy()")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny() {
            Log.e(TAG, "onAny()")
        }
    }
}
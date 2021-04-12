package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import com.example.jetpackdemeo.activity.LifeCycleTestActivity1
import com.example.jetpackdemeo.context.ContextTestActivity
import com.example.jetpackdemeo.databinding.ActivityJetpackDemoLayoutBinding
import com.example.jetpackdemeo.dialog.DialogTestActivity
import com.example.jetpackdemeo.fragment.FragmentTestActivity
import com.example.jetpackdemeo.handler.HandlerTestActivity
import com.example.jetpackdemeo.ipc.IpcActivity
import com.example.jetpackdemeo.multithreading.MultithreadingTestActivity
import com.example.jetpackdemeo.thirdframework.ThirdFrameworkTestActivity
import com.example.jetpackdemeo.toast.ToastTestActivity
import com.example.jetpackdemeo.view.OnePixelView
import com.example.jetpackdemeo.view.ViewUiTestActivity
import com.example.jetpackdemeo.view.recyclerview.AutoScrollRecyclerViewActivity
import com.example.jetpackdemeo.view.recyclerview.SimpleRecyclerViewActivity
import com.example.jetpackdemeo.webview.WebViewActivity

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJetpackDemoLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(JetPackApplication.TAG, "Activity.onCreate() start")

        binding = ActivityJetpackDemoLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        (window.decorView as ViewGroup).addView(OnePixelView(this@DemoActivity).addListener(object :
            OnePixelView.OnePixelViewOnListener {
            override fun onMeasure() {
                Log.e(JetPackApplication.TAG, "onePixelView.onMeasure()")
            }

            override fun onLayout() {
                Log.e(JetPackApplication.TAG, "window.onePixelView.onLayout()")
            }

            override fun onDraw() {
                Log.e(JetPackApplication.TAG, "window.onePixelView.onDraw()")
            }
        }))

        window.decorView.doOnPreDraw {
            Log.e(JetPackApplication.TAG, "window.decorVie.onPreDraw()")
        }
        window.decorView.post {
            Log.e(JetPackApplication.TAG, "window.decorVie.post(Runnable)")
        }

        init()

        Log.e(JetPackApplication.TAG, "Activity.onCreate() end")
    }

    override fun onResume() {
        Log.e(JetPackApplication.TAG, "Activity.onResume() start")

        super.onResume()

        Log.e(JetPackApplication.TAG, "Activity.onResume() end")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Log.e(JetPackApplication.TAG, "onWindowFocusChanged()")
    }

    private fun init() {
        initLagListener()

        initView()

        initListener()
    }

    private fun initView() {
        binding.tvTitleJetpackDemo.text = "JetPack Demo"
        binding.btnActivityJetpackDemo.text = "Start JetPack Activity"
    }

    private fun initListener() {
        binding.apply {
            btnStartActivity.setOnClickListener {
                startActivity(Intent(this@DemoActivity, LifeCycleTestActivity1::class.java))
            }

            btnStartFragment.setOnClickListener {
                startActivity(Intent(this@DemoActivity, FragmentTestActivity::class.java))
            }

            btnStartContext.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ContextTestActivity::class.java))
            }

            btnStartView.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ViewUiTestActivity::class.java))
            }

            btnStartRecyclerview.setOnClickListener {
                startActivity(Intent(this@DemoActivity, AutoScrollRecyclerViewActivity::class.java))
            }

            btnStartRecyclerview5.setOnClickListener {
                startActivity(
                    Intent(this@DemoActivity, SimpleRecyclerViewActivity::class.java).putExtra(
                        SimpleRecyclerViewActivity.SPAN_COUNT,
                        SimpleRecyclerViewActivity.SPAN_COUNT_5
                    )
                )
            }

            btnStartRecyclerview10.setOnClickListener {
                startActivity(
                    Intent(this@DemoActivity, SimpleRecyclerViewActivity::class.java).putExtra(
                        SimpleRecyclerViewActivity.SPAN_COUNT,
                        SimpleRecyclerViewActivity.SPAN_COUNT_10
                    )
                )
            }

            btnStartHandler.setOnClickListener {
                startActivity(Intent(this@DemoActivity, HandlerTestActivity::class.java))
            }

            btnStartToast.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ToastTestActivity::class.java))
            }

            btnShowDialog.setOnClickListener {
                startActivity(Intent(this@DemoActivity, DialogTestActivity::class.java))
            }

            btnShowWebview.setOnClickListener {
                startActivity(Intent(this@DemoActivity, WebViewActivity::class.java))
            }

            btnStartThread.setOnClickListener {
                startActivity(Intent(this@DemoActivity, MultithreadingTestActivity::class.java))
            }

            btnStartThirdframework.setOnClickListener {
                startActivity(Intent(this@DemoActivity, ThirdFrameworkTestActivity::class.java))
            }

            btnActivityJetpackDemo.setOnClickListener {
                startActivity(Intent(this@DemoActivity, JetPackActivity::class.java))
            }

            btnActivityIpc.setOnClickListener {
                startActivity(Intent(this@DemoActivity, IpcActivity::class.java))
            }
        }
    }

    private fun initLagListener() {
        Looper.getMainLooper().setMessageLogging(CustomUILagPrinter())
    }
}
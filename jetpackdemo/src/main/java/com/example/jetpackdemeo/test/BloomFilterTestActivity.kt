package com.example.jetpackdemeo.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityBloomfilterLayoutBinding
import com.google.common.hash.BloomFilter
import com.google.common.hash.Funnels
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy
import java.util.concurrent.TimeUnit
import kotlin.text.Charsets.UTF_8

class BloomFilterTestActivity : AppCompatActivity() {

    private var mViewBinding :ActivityBloomfilterLayoutBinding? = null

    private var mBloomFilter: BloomFilter<String>? = null
    private val mThreadPool = ThreadPoolExecutor(
        4,
        4,
        60,
        TimeUnit.SECONDS,
        ArrayBlockingQueue(16),
        Executors.defaultThreadFactory(),
        DiscardOldestPolicy()
    )

    @Volatile
    private var mHasStart = false
    private val mDataCount = 0
    private var mPrecision = PRECISION_0_03
    private var mDataCountStr = "一百条数据"
    private var mPrecisionStr = "3%"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = ActivityBloomfilterLayoutBinding.inflate(layoutInflater)

        setContentView(mViewBinding?.root)

        init()
    }

    private fun init() {
        mViewBinding?.apply {
            btn003.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_03
                mPrecisionStr = "3%"
            })
            btn001.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_01
                mPrecisionStr = "1%"
            })
            btn0003.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_003
                mPrecisionStr = "0.3%"
            })
            btn0001.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_001
                mPrecisionStr = "0.1%"
            })
            btn00003.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_0003
                mPrecisionStr = "0.03%"
            })
            btn00001.setOnClickListener(View.OnClickListener {
                mPrecision = PRECISION_0_0001
                mPrecisionStr = "0.01%"
            })

            btn100data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一百条数据"
                start(100, "一百")
            })
            btn1000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一千条数据"
                start(1000, "一千")
            })
            btn10000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一万条数据"
                start(10000, "一万")
            })
            btn100000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "十万条数据"
                start(100000, "十万")
            })
            btn1000000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一百万条数据"
                start(1000000, "一百万")
            })
            btn10000000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一千万条数据"
                start(10000000, "一千万")
            })
            btn100000000data.setOnClickListener(View.OnClickListener {
                mDataCountStr = "一亿条数据"
                start(100000000, "一亿")
            })
        }
    }

    private fun start(dataCount: Int, dataCountStr: String) {
        if (mHasStart) {
            return
        }
        mHasStart = true
        mViewBinding?.tvStatus?.text = "数据$mDataCountStr, 精度$mPrecisionStr"
        mThreadPool.execute {
            Log.e(TAG, "start() " + dataCountStr + "条数据")
            initData(dataCount, dataCountStr)
            checkData(dataCountStr)
            mHasStart = false
        }
    }

    private fun initData(dataCount: Int, dataCountStr: String) {
        if (mDataCount != dataCount) {
            mBloomFilter = null
            mBloomFilter = BloomFilter.create(Funnels.stringFunnel(UTF_8), dataCount)
            Log.e(TAG, "initData() " + dataCountStr + "条数据， start")
            for (i in 0 until dataCount) {
                mBloomFilter?.put("" + i)
            }
            Log.e(TAG, "initData() " + dataCountStr + "条数据， end")
        } else {
            Log.e(TAG, "initData() " + dataCountStr + "数据不变")
        }
    }

    private fun checkData(dataCountStr: String) {
        Log.e(TAG, "checkData() " + dataCountStr + "条数据, start")
        val contain = mBloomFilter?.mightContain("0")
        Log.e(TAG, "checkData() " + dataCountStr + "条数据, end")
    }

    companion object {
        private val TAG = BloomFilterTestActivity::class.java.simpleName

        // 一百
        private const val ONE_HUNDRED = 100

        // 一千
        private const val ONE_THOUSAND = 1000

        // 一万
        private const val TEN_THOUSAND = 10000

        // 十万
        private const val ONE_HUNDRED_THOUSAND = 100000

        // 一百万
        private const val ONE_MILLION = 1000000

        // 一千百
        private const val TEN_MILLION = 10000000

        // 一亿
        private const val ONE_HUNDRED_MILLION = 1000000000
        private const val PRECISION_0_03 = 0.03
        private const val PRECISION_0_01 = 0.01
        private const val PRECISION_0_003 = 0.003
        private const val PRECISION_0_001 = 0.001
        private const val PRECISION_0_0003 = 0.0003
        private const val PRECISION_0_0001 = 0.0001
    }
}
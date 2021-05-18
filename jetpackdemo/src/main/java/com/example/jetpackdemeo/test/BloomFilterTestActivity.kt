package com.example.jetpackdemeo.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityBloomfilterLayoutBinding
import com.example.jetpackdemeo.webview.WebViewActivity
import com.example.jetpackdemeo.webview.WebViewActivity.Companion.LOAD_URL
import com.google.common.hash.BloomFilter
import com.google.common.hash.Funnels
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.text.Charsets.UTF_8

// 需要注意的是，String和输入输出流的转化需要使用Charsets.ISO_8859_1
class BloomFilterTestActivity : AppCompatActivity() {

    private var mViewBinding: ActivityBloomfilterLayoutBinding? = null

    private var mBloomFilter: BloomFilter<String>? = null

    @Volatile
    private var mHasStart = false
    private var mDataCount = 0
    private var mPrecision = PRECISION_0_03
    private var mDataCountStr = "一百条数据"
    private var mPrecisionStr = "3%"

    private val mSharedPreferences by lazy { getSharedPreferences(AB_FILE, Context.MODE_PRIVATE) }
    private var mOriginalBloomFilter: BloomFilter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = ActivityBloomfilterLayoutBinding.inflate(layoutInflater)

        setContentView(mViewBinding?.root)

        init()
    }

    private fun init() {
        mViewBinding?.apply {
            btn003.setOnClickListener {
                mPrecision = PRECISION_0_03
                mPrecisionStr = "3%"
            }
            btn001.setOnClickListener {
                mPrecision = PRECISION_0_01
                mPrecisionStr = "1%"
            }
            btn0003.setOnClickListener {
                mPrecision = PRECISION_0_003
                mPrecisionStr = "0.3%"
            }
            btn0001.setOnClickListener {
                mPrecision = PRECISION_0_001
                mPrecisionStr = "0.1%"
            }
            btn00003.setOnClickListener {
                mPrecision = PRECISION_0_0003
                mPrecisionStr = "0.03%"
            }
            btn00001.setOnClickListener {
                mPrecision = PRECISION_0_0001
                mPrecisionStr = "0.01%"
            }

            btn100data.setOnClickListener {
                mDataCountStr = "一百条数据"
                start(ONE_HUNDRED, "一百")
            }
            btn1000data.setOnClickListener {
                mDataCountStr = "一千条数据"
                start(ONE_THOUSAND, "一千")
            }
            btn2000data.setOnClickListener {
                mDataCountStr = "两千条数据"
                start(TWO_THOUSAND, "两千")
            }
            btn10000data.setOnClickListener {
                mDataCountStr = "一万条数据"
                start(TEN_THOUSAND, "一万")
            }
            btn100000data.setOnClickListener {
                mDataCountStr = "十万条数据"
                start(ONE_HUNDRED_THOUSAND, "十万")
            }
            btn1000000data.setOnClickListener {
                mDataCountStr = "一百万条数据"
                start(ONE_MILLION, "一百万")
            }
            btn10000000data.setOnClickListener {
                mDataCountStr = "一千万条数据"
                start(TEN_MILLION, "一千万")
            }
            btn100000000data.setOnClickListener {
                mDataCountStr = "一亿条数据"
                start(ONE_HUNDRED_MILLION, "一亿")
            }
        }
    }

    private fun start(dataCount: Int, dataCountStr: String) {
        if (mHasStart) {
            return
        }

        mHasStart = true
        mViewBinding?.tvStatus?.text = "数据$dataCountStr, 精度$mPrecisionStr"

        test(dataCount)
    }

    private fun test(dataCount: Int) {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
            try {
                Log.e(TAG, "start() $mDataCount 条数据")
                // 模拟插入数据
                initData(dataCount, mDataCountStr)
                // 模拟查询数据
                emitter.onNext(checkData(mDataCountStr))
            } catch (e: Exception) {
                emitter.onError(e)
                emitter.onNext("https://www.baidu.com")
            } finally {
                emitter.onComplete()
            }
        }).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    startWeb(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    mHasStart = false
                }
            })
    }

    private fun initData(dataCount: Int, dataCountStr: String) {
        if (dataCount != mDataCount) {

            mDataCount = dataCount

            Log.e(TAG, "initData() " + dataCountStr + "条数据， start")

            generateBloomFilter(dataCount)

            writeBloomFilterToSp()

            Log.e(TAG, "initData() " + dataCountStr + "条数据， end")
        } else {
            Log.e(TAG, "initData() " + dataCountStr + "条数据， start")

            if (!readBloomFilterFromSp()) {
                generateBloomFilter(dataCount)

                writeBloomFilterToSp()
            }

            Log.e(TAG, "initData() " + dataCountStr + "条数据， end")
        }
    }

    private fun checkData(dataCountStr: String): String {
        Log.e(TAG, "checkData() " + dataCountStr + "条数据, start")
        val contain = mBloomFilter?.mightContain("10000") ?: false
        mBloomFilter = null
        Log.e(TAG, "checkData() " + dataCountStr + "条数据, 查询结果 $contain,  end")

        return if (contain) {
            "https:///www.bilibili.com"
        } else {
            "https://www.baidu.com"
        }
    }

    private fun readBloomFilterFromSp(): Boolean {
        mBloomFilter = BloomFilterUtil.readBloomFilterFromSp(this, AB_FILE, BLOOM_FILTER)

        Log.e(
            TAG,
            "readBloomFilterFromSp() mBloomFilter == mOriginalBloomFilter = ${
                mBloomFilter?.equals(mOriginalBloomFilter)
            }"
        )

        return mBloomFilter == null

//        val string: String = mSharedPreferences.getString(BLOOM_FILTER, "").toString()
//
//        return if (string == null || string == "") {
//            false
//        } else {
//            string.apply {
//                // 注意，此处需要使用Charsets.ISO_8859_1
//                mBloomFilter = BloomFilter.readFrom(
//                    ByteArrayInputStream(toByteArray(Charsets.ISO_8859_1)),
//                    Funnels.stringFunnel(UTF_8)
//                )
//
//
//            }
//
//            true
//        }
    }

    private fun generateBloomFilter(dataCount: Int) {
        mOriginalBloomFilter = null
        mOriginalBloomFilter = BloomFilter.create(Funnels.stringFunnel(UTF_8), dataCount)
        for (i in 0 until dataCount) {
            mOriginalBloomFilter?.put("" + i)
        }
        mBloomFilter = mOriginalBloomFilter
    }

    private fun writeBloomFilterToSp() {
        BloomFilterUtil.writeBloomFilterToSp(mBloomFilter, this, AB_FILE, BLOOM_FILTER)

//        try {
//            val outputStream = ByteArrayOutputStream()
//            mBloomFilter?.writeTo(outputStream)
//
//            // 注意，此处需要使用Charsets.ISO_8859_1
//            val string = String(outputStream.toByteArray(), Charsets.ISO_8859_1)
//
//            val outputStream2 = ByteArrayOutputStream()
//            outputStream2.write(string.toByteArray(Charsets.ISO_8859_1))
//
//            mSharedPreferences.edit().putString(BLOOM_FILTER, string).apply()
//
//            outputStream.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    private fun startWeb(loadUrl: String) {
        startActivity(Intent(this, WebViewActivity::class.java).putExtra(LOAD_URL, loadUrl))
    }

    companion object {
        private val TAG = BloomFilterTestActivity::class.java.simpleName

        private const val AB_FILE = "AB_SP_FILE"
        private const val BLOOM_FILTER = "BLOOM_FILTER"

        // 一百
        private const val ONE_HUNDRED = 100

        // 一千
        private const val ONE_THOUSAND = 1000

        // 两千
        private const val TWO_THOUSAND = 2000

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
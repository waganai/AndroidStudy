package com.example.jetpackdemeo.thirdframework.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRetrofitLayoutBinding
import com.example.jetpackdemeo.thirdframework.retrofit.data.Translation
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitTestActivity : AppCompatActivity() {

    companion object{
        val TAG = RetrofitTestActivity::class.simpleName
    }

    var viewBindings: ActivityRetrofitLayoutBinding? = null
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v1.alapi.cn")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private var mDispose :Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityRetrofitLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)
    }

    override fun onDestroy() {
        super.onDestroy()

        mDispose?.apply {
            if (!isDisposed){
                dispose()
            }
        }
    }

    fun onNetPollingClick(view: View) {
        Observable.interval(1, TimeUnit.MINUTES)
            .flatMap {
                mRetrofit.create(GetRequestInterface::class.java).translate()
            }.subscribeOn(Schedulers.io())
            .subscribe(object : Observer<Translation> {
                override fun onSubscribe(d: Disposable) {
                    mDispose = d
                }

                override fun onNext(t: Translation) {
                    Log.e(TAG, "onNext() ${t.toString()}")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError() ${e.message}")
                }

                override fun onComplete() {
                }
            })
    }
}
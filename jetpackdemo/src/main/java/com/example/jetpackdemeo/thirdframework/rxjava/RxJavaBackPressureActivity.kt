package com.example.jetpackdemeo.thirdframework.rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityBackpressureLayoutBinding
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class RxJavaBackPressureActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaBackPressureActivity::class.simpleName
    }

    var viewBindings: ActivityBackpressureLayoutBinding? = null

    private var mBackPressure = false
    private var mBackPressureError = false
    private var mBackPressureMissing = false
    private var mBackPressureBuffer = false
    private var mBackPressureDrop = false
    private var mBackPressureLatest = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityBackpressureLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        mBackPressure = false
        mBackPressureError = false
        mBackPressureMissing = false
        mBackPressureBuffer = false
        mBackPressureDrop = false
        mBackPressureLatest = false
    }

    private fun init() {
    }

    fun onBackPressure(view: View) {
        if (mBackPressure) {
            return
        }

        mBackPressure = true


        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                var value = 0
                while (mBackPressure) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "subscribe()  emitter.onNext($value)")
                }
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onNext() t = $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }
            })
    }

    fun onBackPressureError(view: View) {
        if (mBackPressureError) {
            return
        }

        mBackPressureError = true

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>) {
                var value = 0
                while (mBackPressureError) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "onBackPressureError.subscribe() emitter.onNext($value)")
                }
            }
        }, BackpressureStrategy.ERROR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Int?) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onBackPressureError onNext($t)")
                }

                override fun onError(t: Throwable?) {
                    Log.e(TAG, "onBackPressureError onError($t)")

                    mBackPressureError = false
                }

                override fun onComplete() {
                }
            })
    }

    fun onBackPressureMissing(view: View) {
        if (mBackPressureMissing) {
            return
        }

        mBackPressureMissing = true

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>) {
                var value = 0
                while (mBackPressureMissing) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "onBackPressureMissing.subscribe() emitter.onNext($value)")
                }
            }
        }, BackpressureStrategy.MISSING)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Int?) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onBackPressureMissing onNext($t)")
                }

                override fun onError(t: Throwable?) {
                    Log.e(TAG, "onBackPressureMissing onError($t)")

                    mBackPressureMissing = false
                }

                override fun onComplete() {
                }
            })
    }

    fun onBackPressureBuffer(view: View) {
        if (mBackPressureBuffer) {
            return
        }

        mBackPressureBuffer = true

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>) {
                var value = 0
                while (mBackPressureBuffer) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "onBackPressureBuffer.subscribe() emitter.onNext($value)")
                }
            }
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Int?) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onBackPressureBuffer onNext($t)")
                }

                override fun onError(t: Throwable?) {
                    Log.e(TAG, "onBackPressureBuffer onError($t)")

                    mBackPressureBuffer = false
                }

                override fun onComplete() {
                }
            })
    }

    fun onBackPressureDrop(view: View) {
        if (mBackPressureDrop) {
            return
        }

        mBackPressureDrop = true

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>) {
                var value = 0
                while (mBackPressureDrop) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "onBackPressureDrop.subscribe() emitter.onNext($value)")
                }
            }
        }, BackpressureStrategy.DROP)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Int?) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onBackPressureDrop onNext($t)")
                }

                override fun onError(t: Throwable?) {
                    Log.e(TAG, "onBackPressureDrop onError($t)")

                    mBackPressureDrop = false
                }

                override fun onComplete() {
                }
            })
    }

    fun onBackPressureLatesd(view: View) {
        if (mBackPressureLatest) {
            return
        }

        mBackPressureLatest = true

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>) {
                var value = 0
                while (mBackPressureLatest) {
                    Thread.sleep(10L)

                    emitter.onNext(value++)

                    Log.d(TAG, "onBackPressureLatest.subscribe() emitter.onNext($value)")
                }
            }
        }, BackpressureStrategy.LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Int?) {
                    Thread.sleep(20L)

                    Log.d(TAG, "onBackPressureLatest onNext($t)")
                }

                override fun onError(t: Throwable?) {
                    Log.e(TAG, "onBackPressureLatest onError($t)")

                    mBackPressureLatest = false
                }

                override fun onComplete() {
                }
            })
    }
}
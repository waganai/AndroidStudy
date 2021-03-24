package com.example.jetpackdemeo.thirdframework.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaCreateOperatorLayoutBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RxJavaCreateOperatorActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaCreateOperatorActivity::class.simpleName
    }

    private val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    var viewBindings: ActivityRxjavaCreateOperatorLayoutBinding? = null
    private var timeStr: String = format.format(Date(System.currentTimeMillis()))
    private val deferObservable = Observable.defer {
        Observable.just<String>(
            timeStr
        )
    }

    private val justObservable = Observable.just(timeStr)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityRxjavaCreateOperatorLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.apply {
            btnCreate.setOnClickListener {
                create()
            }

            btnJust.setOnClickListener {
                just()
            }

            btnJust2.setOnClickListener {
                just2()
            }

            btnFromArray.setOnClickListener {
                fromArray()
            }

            btnFromIterable.setOnClickListener {
                fromIterable()
            }

            btnEmpty.setOnClickListener {
                empty()
            }

            btnError.setOnClickListener {
                error()
            }

            btnNever.setOnClickListener {
                never()
            }

            btnDefer.setOnClickListener {
                defer()
            }

            btnTimer.setOnClickListener {
                timer()
            }

            btnInterval.setOnClickListener {
                interval()
            }

            btnIntervalRange.setOnClickListener {
                intervalRange()
            }

            btnRange.setOnClickListener {
                range()
            }

            btnRangeLong.setOnClickListener {
                rangeLong()
            }
        }
    }

    private fun create() {
        Observable.create<Int> {
            it.onNext(0)
            it.onNext(1)
            it.onNext(2)
            it.onNext(233)
            it.onNext(666)

            it.onComplete()
        }.subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "create() onSubscribe($d)")
            }

            override fun onNext(t: Int) {
                Log.e(TAG, "create() onNext($t)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "create() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "create() onComplete()")
            }
        })
    }

    private fun just() {
        // 只应该用来发射10个及以下数据
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "just() onSubscribe($d)")
                }

                override fun onNext(t: Int) {
                    Log.e(TAG, "just() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "just() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "just() onComplete()")
                }
            })
    }

    private fun just2() {
        timeStr = format.format(System.currentTimeMillis())
        // 只应该用来发射10个及以下数据
        justObservable
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "just2() onSubscribe($d)")
                }

                override fun onNext(t: String) {
                    Log.e(TAG, "just2() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "just2() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "just2() onComplete()")
                }
            })
    }

    private fun fromArray() {
        Observable.fromArray(
            *arrayOf(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                0,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20
            )
        ).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "fromArray() onSubscribe($d)")
            }

            override fun onNext(a: Int) {
                Log.e(TAG, "fromArray() onNext($a)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "fromArray() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "fromArray() onComplete()")
            }
        })
    }

    private fun fromIterable() {
        Observable.fromIterable(listOf("A", "B", "C"))
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "fromIterable() onSubscribe($d)")
                }

                override fun onNext(s: String) {
                    Log.e(TAG, "fromIterable() onNext($s)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "fromIterable() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "fromIterable() onComplete()")
                }
            })
    }

    private fun empty() {
        Observable.empty<String>().subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "empty() onSubscribe($d)")
            }

            override fun onNext(s: String) {
                Log.e(TAG, "empty() onNext($s)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "empty() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "empty() onComplete()")
            }
        })
    }

    private fun error() {
        Observable.error<String>(Throwable())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "error() onSubscribe($d)")
                }

                override fun onNext(s: String) {
                    Log.e(TAG, "error() onNext($s)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "error() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "error() onComplete()")
                }
            })
    }

    private fun never() {
        Observable.never<String>()
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "never() onSubscribe($d)")
                }

                override fun onNext(s: String) {
                    Log.e(TAG, "never() onNext($s)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "never() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "never() onComplete()")
                }
            })
    }

    private fun defer() {
        timeStr = format.format(System.currentTimeMillis())

        deferObservable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "defer() onSubscribe($d)")
            }

            override fun onNext(s: String) {
                Log.e(TAG, "defer() onNext($s)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "defer() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "defer() onComplete()")
            }
        })
    }

    private fun timer() {
        Log.e(TAG, "timer()")
        Observable
            .timer(2, TimeUnit.SECONDS)
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "defer() onSubscribe($d)")
                }

                override fun onNext(l: Long) {
                    Log.e(TAG, "defer() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "defer() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "defer() onComplete()")
                }
            })
    }

    private fun interval() {
        Log.e(TAG, "interval()")

        Observable.interval(1, 2, TimeUnit.SECONDS)
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "interval() onSubscribe($d)")
                }

                override fun onNext(l: Long) {
                    Log.e(TAG, "interval() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "interval() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "interval() onComplete()")
                }
            })
    }

    private fun intervalRange() {
        Log.e(TAG, "intervalRange()")

        Observable.intervalRange(10, 20, 1, 1, TimeUnit.SECONDS)
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "intervalRange() onSubscribe($d)")
                }

                override fun onNext(l: Long) {
                    Log.e(TAG, "intervalRange() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "intervalRange() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "intervalRange() onComplete()")
                }
            })
    }

    private fun range() {
        Log.e(TAG, "range()")

        Observable.range(10, 9).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "range() onSubscribe($d)")
            }

            override fun onNext(i: Int) {
                Log.e(TAG, "range() onNext($i)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "range() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "range() onComplete()")
            }
        })
    }

    private fun rangeLong() {
        Log.e(TAG, "rangeLong()")

        Observable.rangeLong(2, 8).subscribe(object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "rangeLong() onSubscribe($d)")
            }

            override fun onNext(l: Long) {
                Log.e(TAG, "rangeLong() onNext($l)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "rangeLong() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "rangeLong() onComplete()")
            }
        })
    }
}
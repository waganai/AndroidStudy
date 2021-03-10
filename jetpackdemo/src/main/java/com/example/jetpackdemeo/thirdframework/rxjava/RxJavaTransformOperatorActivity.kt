package com.example.jetpackdemeo.thirdframework.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaTransformLayoutBinding
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxJavaTransformOperatorActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaTransformOperatorActivity::class.simpleName
    }

    var viewBindings: ActivityRxjavaTransformLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityRxjavaTransformLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.apply {
            btnMap.setOnClickListener {
                map()
            }

            btnFlatMap.setOnClickListener {
                flatMap()
            }

            btnConcatMap.setOnClickListener {
                concatMap()
            }

            btnBuffer.setOnClickListener {
                buffer()
            }
        }
    }

    private fun map() {
        Observable.just(1, 2, 3).map { t ->
            val s = (t * t).toString()
            Log.e(TAG, "map() apply() s = $s")
            s
        }.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "map() onSubscribe($d)")
            }

            override fun onNext(t: String) {
                Log.e(TAG, "map() onNext($t)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "map() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "map() onComplete()")
            }
        })
    }

    private fun flatMap() {
        Observable.just("A", "B", "C", "D", "E", "F")
            .flatMap { t ->
                val list = mutableListOf<String>()

                for (i in 0..2) {
                    val s = "i = $i, $t"
                    Log.e(TAG, "flatMap() apply() s = $s")

                    list.add(i, s)
                }

                Observable.fromIterable(list)
            }
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "flatMap() onSubscribe($d)")
                }

                override fun onNext(t: String) {
                    Log.e(TAG, "flatMap() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "flatMap() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "flatMap() onComplete()")
                }
            })
    }

    private fun concatMap() {
        Observable.just("A", "B", "C", "D", "E", "F")
            .concatMap { t ->
                val list = mutableListOf<String>()

                for (i in 0..2) {
                    val s = "i = $i, $t"
                    Log.e(TAG, "concatMap() apply() s = $s")

                    list.add(i, s)
                }

                Observable.fromIterable(list)
            }.subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "concatMap() onSubscribe($d)")
                }

                override fun onNext(t: String) {
                    Log.e(TAG, "concatMap() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "concatMap() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "concatMap() onComplete()")
                }
            })
    }

    private fun buffer() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onNext(4)
                emitter.onNext(5)
                emitter.onNext(6)
                emitter.onNext(7)
                emitter.onNext(8)
                emitter.onNext(9)
                emitter.onNext(0)
            }
        })
            .buffer(3, 2)
            .subscribe(object : Observer<List<Int>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "buffer() onSubscribe($d)")
                }

                override fun onNext(l: List<Int>) {
                    Log.e(TAG, "buffer() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "buffer() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "buffer() onComplete()")
                }
            })
    }
}
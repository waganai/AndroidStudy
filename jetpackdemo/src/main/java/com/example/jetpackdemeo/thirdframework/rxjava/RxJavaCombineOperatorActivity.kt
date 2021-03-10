package com.example.jetpackdemeo.thirdframework.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaCombineOperatorLayoutBinding
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class RxJavaCombineOperatorActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaCombineOperatorActivity::class.simpleName
    }

    var viewBindings: ActivityRxjavaCombineOperatorLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityRxjavaCombineOperatorLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.apply {
            btnConcat.setOnClickListener {
                concat()
            }

            btnConcatArray.setOnClickListener {
                concatArray()
            }

            btnMerge.setOnClickListener {
                merge()
            }

            btnMergeArray.setOnClickListener {
                mergeArray()
            }

            btnConcatDelayError.setOnClickListener {
                concatDelayError()
            }

            btnMergeDelayError.setOnClickListener {
                mergeDelayError()
            }

            btnZip.setOnClickListener {
                zip()
            }

            btnCombineLatest.setOnClickListener {
                combineLatest()
            }

            btnCombineLatestDelayError.setOnClickListener {

            }

            btnReduce.setOnClickListener {
                reduce()
            }

            btnCollect.setOnClickListener {
                collect()
            }

            btnStartWith.setOnClickListener {
                startWith()
            }

            btnStartWithArray.setOnClickListener {
                startWithArray()
            }

            btnCount.setOnClickListener {
                count()
            }
        }
    }

    private fun concat() {
        Observable.concat(
            Observable.just(1, 2),
            Observable.just(2),
            Observable.just(3),
            Observable.just(4)
        )
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "concat() onSubscribe($d)")
                }

                override fun onNext(t: Int) {
                    Log.e(TAG, "concat() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "concat() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "concat() onComplete()")
                }
            })
    }

    private fun concatArray() {
        Observable.concatArray(
            Observable.just(1, 2, 3),
            Observable.just(4, 5, 6),
            Observable.just(7, 8, 9),
            Observable.just(10, 11, 12),
            Observable.just(13, 14, 15),
            Observable.just(16, 17, 18),
            Observable.just(19, 20, 21),
        )
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "concatArray() onSubscribe($d)")
                }

                override fun onNext(t: Int) {
                    Log.e(TAG, "concatArray() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "concatArray() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "concatArray() onComplete()")
                }
            })
    }

    private fun merge() {
        Observable.merge(
            Observable.intervalRange(10, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
        )
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "merge() onSubscribe($d)")
                }

                override fun onNext(l: Long) {
                    Log.e(TAG, "merge() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "merge() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "merge() onComplete()")
                }
            })
    }

    private fun mergeArray() {
        Observable.mergeArray(
            Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(10, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(20, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(30, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(40, 10, 1, 1, TimeUnit.SECONDS),
            Observable.intervalRange(50, 10, 1, 1, TimeUnit.SECONDS)
        )
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "mergeArray() onSubscribe($d)")
                }

                override fun onNext(l: Long) {
                    Log.e(TAG, "mergeArray() onNext($l)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "mergeArray() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "mergeArray() onComplete()")
                }
            })
    }

    private fun concatDelayError() {
        Observable.concatArrayDelayError(Observable.create(ObservableOnSubscribe<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3)
            emitter.onError(NullPointerException())
            emitter.onNext(4)
            emitter.onComplete()
        }), Observable.just(10, 11, 12))
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "concatDelayError() onSubscribe($d)")
                }

                override fun onNext(i: Int) {
                    Log.e(TAG, "concatDelayError() onNext($i)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "concatDelayError() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "concatDelayError() onComplete()")
                }
            })
    }

    private fun mergeDelayError() {
        Observable.mergeArrayDelayError(Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(0)
                emitter.onNext(1)
                emitter.onError(NullPointerException())
                emitter.onNext(2)
                emitter.onNext(3)
            }

        }), Observable.just(10, 11, 12, 13))
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "mergeDelayError() onSubscribe($d)")
                }

                override fun onNext(i: Int) {
                    Log.e(TAG, "mergeDelayError() onNext($i)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "mergeDelayError() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "mergeDelayError() onComplete()")
                }
            })
    }

    private fun zip() {
        Observable.zip(Observable.just(1, 2, 3),
            Observable.just("A", "B", "C", "D"),
            { t1, t2 -> "t1 = $t1, t2 = $t2" }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "zip() onSubscribe($d)")
            }

            override fun onNext(s: String) {
                Log.e(TAG, "zip() onNext($s)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "zip() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "zip() onComplete()")
            }
        })
    }

    private fun combineLatest() {
        Observable.combineLatest(
            Observable.just("A", "B", "C"),
            Observable.intervalRange(0, 5, 2, 2, TimeUnit.SECONDS),
            { t1, t2 -> "t1 = $t1， t2 = $t2" }
        ).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "combineLatest() onSubscribe($d)")
            }

            override fun onNext(s: String) {
                Log.e(TAG, "combineLatest() onNext($s)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "combineLatest() onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "combineLatest() onComplete()")
            }
        })
    }

    private fun reduce() {
        Observable.just(1, 2, 3, 4, 5)
            .reduce { t1, t2 -> t1 * t2 }
            .subscribe(object : MaybeObserver<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "reduce() onSubscribe($d)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "reduce() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "reduce() onComplete()")
                }

                override fun onSuccess(i: Int) {
                    Log.e(TAG, "reduce() onSuccess($i)")
                }
            })
    }

    private fun collect() {
        Observable.just(1, 2, 3, 4)
            .collect({ ArrayList<Int>() }
            ) { t1, t2 -> t1?.add(t2 ?: 0) }.subscribe(object : SingleObserver<ArrayList<Int>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "collect() onSubscribe($d)")
                }

                override fun onSuccess(t: ArrayList<Int>) {
                    Log.e(TAG, "collect() onSuccess($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "collect() onError($e)")
                }

            })
    }

    private fun startWith() {
        Observable.just(4, 5, 6)
            .startWith(Observable.just(1, 2, 3))
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "startWith() onSubscribe($d)")
                }

                override fun onNext(i: Int) {
                    Log.e(TAG, "startWith() onNext($i)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "startWith() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "startWith() onComplete()")
                }
            })
    }

    private fun startWithArray() {
        Observable.just(10, 11, 12)
            .startWithArray(0, 1, 2)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "startWithArray() onSubscribe($d)")
                }

                override fun onNext(i: Int) {
                    Log.e(TAG, "startWithArray() onNext($i)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "startWithArray() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "startWithArray() onComplete()")
                }
            })
    }

    private fun count() {
        Observable.just(1, 2, 3, 4, 5, 6)
            .count()
            .subscribe(object : Consumer<Long> {
                override fun accept(t: Long?) {
                    Log.e(TAG, "count() accept($t)")
                }
            })
    }

}
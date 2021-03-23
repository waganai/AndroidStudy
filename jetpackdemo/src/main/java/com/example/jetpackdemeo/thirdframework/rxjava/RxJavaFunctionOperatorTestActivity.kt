package com.example.jetpackdemeo.thirdframework.rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaFunctionOperatorLayoutBinding
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxJavaFunctionOperatorTestActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaFunctionOperatorTestActivity::class.simpleName
    }

    var viewBindings: ActivityRxjavaFunctionOperatorLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityRxjavaFunctionOperatorLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

    }

    fun onSubscribe(view: View) {
        val observable = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("subscribe 1")
                emitter.onNext("subscribe 2")
                emitter.onNext("subscribe 3")
                emitter.onNext("subscribe 4")

                emitter.onComplete()
            }
        })

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "onSubscribe(view:View) onSubscribe($d)")
            }

            override fun onNext(t: String) {
                Log.e(TAG, "onSubscribe(view:View) onNext($t)")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onSubscribe(view:View) onError($e)")
            }

            override fun onComplete() {
                Log.e(TAG, "onSubscribe(view:View) onComplete()")
            }
        }

        observable.subscribe(observer)
    }

    fun onSubscribeOn(view: View) {
        Observable.just(
            "subscribe1",
            "subscribe2",
            "subscribe3",
            "subscribe4",
            "subscribe5",
            "subscribe6"
        ).subscribeOn(Schedulers.newThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(
                        TAG,
                        "onSubscribeOn(view: View) onSubscribe($d), ${Thread.currentThread().name}"
                    )
                }

                override fun onNext(t: String) {
                    Log.e(
                        TAG,
                        "onSubscribeOn(view: View) onNext($t), ${Thread.currentThread().name}"
                    )
                }

                override fun onError(e: Throwable) {
                    Log.e(
                        TAG,
                        "onSubscribeOn(view: View) onError($e), ${Thread.currentThread().name}"
                    )
                }

                override fun onComplete() {
                    Log.e(
                        TAG,
                        "onSubscribeOn(view: View) onComplete(), ${Thread.currentThread().name}"
                    )
                }
            })
    }

    fun onObserveOn(view: View) {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("666")
            Log.e(
                TAG,
                "onObservable() ObservableOnSubscribe.subscribe() ${Thread.currentThread().name}"
            )
            emitter.onComplete()
        }).observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.e(
                    TAG,
                    "onObservable() doOnNext{} ${Thread.currentThread().name}"
                )
            }
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(
                        TAG,
                        "onObservable() onSubscribe($d) ${Thread.currentThread().name}"
                    )
                }

                override fun onNext(t: String) {
                    Log.e(
                        TAG,
                        "onObservable() onNext($t) ${Thread.currentThread().name}"
                    )
                }

                override fun onError(e: Throwable) {
                    Log.e(
                        TAG,
                        "onObservable() onError($e) ${Thread.currentThread().name}"
                    )
                }

                override fun onComplete() {
                    Log.e(
                        TAG,
                        "onObservable() onComplete() ${Thread.currentThread().name}"
                    )
                }

            })
    }

    fun onDelay(view: View) {
        Observable.just(1, 2, 3)
            .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "onDelay() onSubscribe($d)")
                }

                override fun onNext(t: Int) {
                    Log.e(TAG, "onDelay() onNext($t)")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onDelay() onError($e)")
                }

                override fun onComplete() {
                    Log.e(TAG, "onDelay() onComplete()")
                }
            })
    }

    fun onDo(view: View) {

    }

    fun onRepeat(view: View) {

    }

    fun onRepeatWhen(view: View) {

    }

    fun onRetry(view: View) {

    }

    fun onRetryUntil(view: View) {

    }

    fun onRetryWhen(view: View) {

    }

    fun onErrorReturn(view: View) {

    }

    fun onErrorResumeNext(view: View) {

    }

    fun onExceptionResumeNext(view: View) {

    }
}
package com.example.jetpackdemeo.thirdframework.rxjava

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaOperatorLayoutBinding
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

class RxJavaOperatorTestActivity : AppCompatActivity() {

    companion object {
        val TAG = RxJavaFunctionOperatorTestActivity::class.simpleName
    }

    var viewBinding: ActivityRxjavaOperatorLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityRxjavaOperatorLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnCreate.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaCreateOperatorActivity::class.java
                    )
                )
            }

            btnTransform.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaTransformOperatorActivity::class.java
                    )
                )
            }

            btnCombine.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaCombineOperatorActivity::class.java
                    )
                )
            }

            btnFunction.setOnClickListener {
                startActivity(
                    Intent(
                        this@RxJavaOperatorTestActivity,
                        RxJavaFunctionOperatorTestActivity::class.java
                    )
                )
            }

            btnBool.setOnClickListener { }

            btnLift.setOnClickListener {
                Observable.just(1, 2, 3, 4, 5)
                    .lift(ObservableOperator<String, Int> { observer ->
                        object : Observer<Int> {
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(t: Int) {
                                observer.onNext("lift 操作符 $t")
                            }

                            override fun onError(e: Throwable) {
                            }

                            override fun onComplete() {
                            }
                        }
                    })
                    .subscribe(object : Observer<String> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: String) {
                            Log.e(TAG, "lift() onNext($t)")
                        }

                        override fun onError(e: Throwable) {
                        }

                        override fun onComplete() {
                        }
                    })
            }

            btnCompose.setOnClickListener {
                Observable.just(1, 2, 3, 4, 5)
                    .compose(object : ObservableTransformer<Int, String> {
                        override fun apply(upstream: Observable<Int>): ObservableSource<String> {
                            return upstream.flatMap(object :
                                Function<Int, ObservableSource<String>> {
                                override fun apply(t: Int): ObservableSource<String> {
                                    return Observable.just("t = $t")
                                }
                            })
                        }
                    })
                    .subscribe(object:Observer<String>{
                        override fun onSubscribe(d: Disposable) {
                            Log.e(TAG, "compose() onSubscribe($d)")
                        }

                        override fun onNext(t: String) {
                            Log.e(TAG, "compose() onNext($t)")
                        }

                        override fun onError(e: Throwable) {
                            Log.e(TAG, "compose() onError($e)")
                        }

                        override fun onComplete() {
                            Log.e(TAG, "compose() onComplete()")
                        }
                    })
            }
        }
    }
}
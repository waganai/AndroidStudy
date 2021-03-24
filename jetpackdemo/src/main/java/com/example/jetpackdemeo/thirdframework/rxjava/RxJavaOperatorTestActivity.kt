package com.example.jetpackdemeo.thirdframework.rxjava

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityRxjavaOperatorLayoutBinding
import io.reactivex.Observable
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxJavaOperatorTestActivity : AppCompatActivity() {

    companion object{
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
                        object: Observer<Int>{
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
                    .subscribe(object:Observer<String>{
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
        }
    }
}
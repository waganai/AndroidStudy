package com.example.jetpackdemeo.thirdframework.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemeo.databinding.ActivityRxjavaTestLayoutBinding;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxJavaTestActivity extends AppCompatActivity {

    private static final String TAG = RxJavaTestActivity.class.getSimpleName();

    ActivityRxjavaTestLayoutBinding viewBindings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBindings = ActivityRxjavaTestLayoutBinding.inflate(getLayoutInflater());

        setContentView(viewBindings.getRoot());

        init();
    }

    private void init() {
        viewBindings.btnRxjavaSeparation.setOnClickListener(v -> {
            rxJavaSeparation();
        });

        viewBindings.btnRxjavaChain.setOnClickListener(v -> {
            rxJavaChain();
        });

        viewBindings.btnRxjavaDispose.setOnClickListener(v -> {
            rxJavaDispose();
        });
    }

    private void rxJavaSeparation() {
        Log.e(TAG, "rxJavaSeparation()");

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(0);
                emitter.onNext(1);
                emitter.onNext(233);
                emitter.onNext(666);

                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe(" + d.toString() + ")");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "onNext(" + integer.intValue() + ")");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError(" + e.getMessage() + ")");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete()");
            }
        };

        observable.subscribe(observer);
    }

    private void rxJavaChain() {
        Log.e(TAG, "rxJavaChain()");

        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext("A");
                    emitter.onNext("B");
                    emitter.onNext("C");
                    emitter.onNext("D");
                    emitter.onNext("E");

                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe(" + d.toString() + ")");
                    }

                    @Override
                    public void onNext(@NonNull String string) {
                        Log.e(TAG, "onNext(" + string + ")");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError(" + e.getMessage() + ")");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete()");
                    }
                });
    }

    private void rxJavaDispose() {
        Log.e(TAG, "rxJavaDispose()");

        final Disposable[] disposable = new Disposable[1];

        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext("1");
                    emitter.onNext("2");
                    emitter.onNext("3");
                    emitter.onNext("4");
                    emitter.onNext("5");

                    emitter.onComplete();
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable[0] = d;
                Log.e(TAG, "onSubscribe(" + d.toString() + ")");
            }

            @Override
            public void onNext(@NonNull String string) {
                Log.e(TAG, "onNext(" + string + ")");

                if ("3".equals(string) && disposable[0] != null) {
                    Log.e(TAG, "onNext() dispose[0].dispose()");
                    disposable[0].dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError(" + e.getMessage() + ")");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete()");
            }
        });
    }
}

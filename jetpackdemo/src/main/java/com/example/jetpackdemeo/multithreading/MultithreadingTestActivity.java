package com.example.jetpackdemeo.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemeo.databinding.ActivityMultithreadingTestLayoutBinding;

public class MultithreadingTestActivity extends AppCompatActivity {

    private static final String TAG = MultithreadingTestActivity.class.getSimpleName();

    private ActivityMultithreadingTestLayoutBinding viewBindings;

    private SynchronizedTestClass1 sync1 = new SynchronizedTestClass1("sync1");
    private SynchronizedTestClass1 sync2 = new SynchronizedTestClass1("sync2");

    private Handler mHandler = new Handler();
    private boolean mRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBindings = ActivityMultithreadingTestLayoutBinding.inflate(getLayoutInflater());

        setContentView(viewBindings.getRoot());

        initListener();
    }

    private void initListener() {
        viewBindings.btnSync1.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    sync1.method4();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    sync2.method4();
                }
            }.start();
        });

        viewBindings.btnSync2.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    sync1.method3();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    sync2.method3();
                }
            }.start();
        });

        viewBindings.btnSync3.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    sync1.method1();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    sync2.method1();
                }
            }.start();
        });

        viewBindings.btnSync4.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    sync1.method5();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    sync2.method5();
                }
            }.start();
        });

        viewBindings.btnSync5.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    sync1.method6();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    sync2.method6();
                }
            }.start();
        });

        viewBindings.btnSync6.setOnClickListener(v -> {
                    new Thread() {
                        @Override
                        public void run() {
                            sync1.method1();
                        }
                    }.start();

                    new Thread() {
                        @Override
                        public void run() {
                            sync1.method2();
                        }
                    }.start();
                }
        );

        viewBindings.btnStopThread.setOnClickListener(v -> {
            if (mRunning) {
                return;
            }

            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();

                    Log.e(TAG, "thread start()");

                    mRunning = true;

                    while (mRunning) {
                    }

                    Log.e(TAG, "thread end()");
                }
            };

            thread.start();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRunning = false;
                }
            }, 10000L);
        });

        viewBindings.btnThreadInterrupt1.setOnClickListener(v -> {
            if (mRunning) {
                return;
            }

            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();

                    Log.e(TAG, "thread start()");

                    mRunning = true;

                    try {
                        Thread.sleep(100000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                        Log.e(TAG, "thread isInterrupted()  = " + isInterrupted());
                        Log.e(TAG, "thread interrupted()  = " + interrupted());
                        Log.e(TAG, "thread interrupted()  = " + interrupted());

                        Log.e(TAG, "thread end() for InterruptedException");
                    }

                    Log.e(TAG, "thread end()");
                }
            };

            thread.start();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    thread.interrupt();
                    mRunning = false;
                }
            }, 15000L);
        });

        viewBindings.btnThreadInterrupt2.setOnClickListener(v -> {
            if (mRunning) {
                return;
            }

            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();

                    Log.e(TAG, "thread start()");

                    mRunning = true;

                    while (true) {
                        boolean flag = isInterrupted();
                        if (flag) {
                            Log.e(TAG, "thread1 isInterrupted()  = " + isInterrupted());
                            Log.e(TAG, "thread1 interrupted()  = " + interrupted());
                            Log.e(TAG, "thread1 interrupted()  = " + interrupted());

                            break;
                        }
                    }

                    Log.e(TAG, "thread end()");
                }
            };

            thread.start();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    thread.interrupt();
                    mRunning = false;
                }
            }, 15000L);
        });

        viewBindings.btnThreadpool.setOnClickListener(v -> {

        });
    }
}

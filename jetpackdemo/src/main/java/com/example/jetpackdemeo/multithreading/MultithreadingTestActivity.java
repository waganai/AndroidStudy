package com.example.jetpackdemeo.multithreading;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemeo.databinding.ActivityMultithreadingTestLayoutBinding;

public class MultithreadingTestActivity extends AppCompatActivity {

    ActivityMultithreadingTestLayoutBinding viewBindings;

    SynchronizedTestClass1 sync1 = new SynchronizedTestClass1("sync1");
    SynchronizedTestClass1 sync2 = new SynchronizedTestClass1("sync2");

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

        viewBindings.btnThreadpool.setOnClickListener(v -> {

        });
    }
}

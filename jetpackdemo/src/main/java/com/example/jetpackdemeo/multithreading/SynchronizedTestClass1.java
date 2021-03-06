package com.example.jetpackdemeo.multithreading;

import android.util.Log;

class SynchronizedTestClass1 {
    private Object obj1 = new Object();
    private static Object obj2 = new Object();

    private String mName = "";

    public SynchronizedTestClass1(String name) {
        mName = name;
    }

    synchronized void method1() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000L);

                Log.e("SynchronizedTestClass1", mName + " method1 i = " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void method2() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000L);

                Log.e("SynchronizedTestClass1", mName + " method2 i = " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void method3() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000L);

                    Log.e("SynchronizedTestClass1", mName + " method3 i = " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void method4() {
        synchronized (SynchronizedTestClass1.class) {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000L);

                    Log.e("SynchronizedTestClass1", mName + " method4 i = " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void method5() {
        synchronized (obj1) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000L);

                    Log.e("SynchronizedTestClass1", mName + " method5 i = " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void method6() {
        synchronized (obj2) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000L);

                    Log.e("SynchronizedTestClass1", mName + " method6 i = " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
package com.hubiao.study

import android.util.Config
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.hubiao.base.BaseApplication

class MyApplication: BaseApplication() {

    companion object{
        val TAG = MyApplication::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        if (Config.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        Log.e(TAG, "onCreate() ARouter.init(this) start")

        ARouter.init(this)

        Log.e(TAG, "onCreate() ARouter.init(this) end")
    }
}
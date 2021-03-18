package com.hubiao.view

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit
import com.didichuxing.doraemonkit.kit.AbstractKit

class SimpleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DoraemonKit.install(SimpleApplication@this,
            LinkedHashMap<String, MutableList<AbstractKit>>(),
            "pId");
    }

}
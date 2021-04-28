package com.example.jetpackdemeo.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.hubiao.base.SERVICE2_INSTANCE1
import com.hubiao.base.iprovider.IProviderService2

@Route(path = SERVICE2_INSTANCE1)
class Service2Instance1 : IProviderService2 {
    override fun fun1() {
    }

    override fun fun2(): Int {
        return 233
    }

    override fun init(context: Context?) {
    }
}
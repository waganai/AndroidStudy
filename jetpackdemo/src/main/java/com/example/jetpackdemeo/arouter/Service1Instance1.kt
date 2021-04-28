package com.example.jetpackdemeo.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.hubiao.base.SERVICE1_INSTANCE1
import com.hubiao.base.iprovider.IProviderService1

@Route(path = SERVICE1_INSTANCE1)
class Service1Instance1:IProviderService1 {
    override fun fun1() {
    }

    override fun fun2(): String {
        return "2333"
    }

    override fun init(context: Context?) {
    }
}
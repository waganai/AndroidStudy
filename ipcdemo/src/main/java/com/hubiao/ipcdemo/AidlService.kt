package com.hubiao.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.hubiao.ipcdemo.bean.User

class AidlService : Service() {

    companion object {
        val TAG = AidlService::class.simpleName
    }

    private val stub = object : IMyAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
        }

        override fun getName(nickName: String?): String {
            Log.e(TAG, "getName() currentThread is ${Thread.currentThread().name}")
            return "aidl is $nickName"
        }

        override fun getUserById(id: Int): User {
            Log.e(TAG, "getUserById() currentThread is ${Thread.currentThread().name}")
            return User("小明", 12)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind($intent)")
        return stub
    }
}
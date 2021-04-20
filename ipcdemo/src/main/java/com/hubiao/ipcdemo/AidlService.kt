package com.hubiao.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.util.Log
import com.hubiao.ipcdemo.bean.User
import java.lang.Exception

class AidlService : Service() {

    companion object {
        val TAG = AidlService::class.simpleName
    }

    private val mUser: User = User("凌波丽1代目", 0)
    private var mGeneration = 1

    private val mListeners: RemoteCallbackList<UserChangeListener> = RemoteCallbackList()

    private val mStub = object : IMyAidlInterface.Stub() {
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
            Log.e(TAG, "getUserById()  start")
            Log.e(TAG, "getUserById() currentThread is ${Thread.currentThread().name}")
            Thread.sleep(2000L)
            Log.e(TAG, "getUserById() end")
            return mUser
        }

        override fun registerListener(listener: UserChangeListener?) {
            mListeners.register(listener)
        }

        override fun unRegisterListener(listener: UserChangeListener?) {
            mListeners.unregister(listener)
        }
    }

    override fun onCreate() {
        super.onCreate()

        Thread {
            while (true) {
                try {
                    if (mUser.mAge <= 14) {
                        mUser.mAge++
                    }

                    if (mGeneration == 1 && mUser.mAge == 9) {
                        mGeneration = 2
                        mUser.mName = "凌波丽${mGeneration}代目"
                        mUser.mAge = 0
                    }

                    if (mGeneration >= 2 && mUser.mAge == 15) {
                        mGeneration++
                        mUser.mName = "凌波丽${mGeneration}代目"
                        mUser.mAge = 14
                    }

                    val count = mListeners.beginBroadcast()

                    Log.e(TAG, "mUser = $mUser, count = $count")

                    for (i in 0 until count) {
                        mListeners.getBroadcastItem(i)?.onUserChanged(mUser)
                    }

                    mListeners.finishBroadcast()

                    Thread.sleep(2000L)
                } catch (e: Exception) {
                    e.printStackTrace()

                    break
                }
            }
        }.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind($intent)")
        return mStub
    }
}
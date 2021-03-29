package com.example.jetpackdemeo.ipc

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityIpcLayoutBinding
import com.hubiao.ipcdemo.IMyAidlInterface

class IpcActivity : AppCompatActivity() {

    companion object {
        val TAG = IpcActivity::class.simpleName
    }

    private var clientMessenger: Messenger? = null
    private var serverMessenger: Messenger? = null
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            Log.e(TAG, "handleMessage() what = ${msg.what}")

            when (msg.what) {
                1 -> {

                }
                2 -> {
                    Log.e(TAG, "name = ${msg.data.getString("name")}")
                }
            }
        }
    }

    private var stub: IMyAidlInterface? = null

    private val messengerConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            try {
                serverMessenger = Messenger(service)
                val message = Message.obtain()
                message.replyTo = clientMessenger
                message.what = 1
                serverMessenger?.send(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    private val aidlConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            stub = IMyAidlInterface.Stub.asInterface(service)

            Log.e(TAG, "Aidl Service connected!")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityIpcLayoutBinding.inflate(layoutInflater).root)

        clientMessenger = Messenger(handler)
    }

    fun onStartMessenger(view: View) {
        bindService(
            Intent().setAction("com.hubiao.ipc.messenger").setPackage("com.hubiao.ipcdemo"),
            messengerConnection,
            Service.BIND_AUTO_CREATE
        )
    }

    fun onMessengerSend1(view: View) {
        try {
            val message = Message.obtain()
            message.what = 2
            val bundle = Bundle()
            bundle.putString("name", "Tony")
            message.data = bundle
            //使用
            serverMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun onStartAidl(view: View) {
        bindService(
            Intent().setAction("com.hubiao.ipc.aidl").setPackage("com.hubiao.ipcdemo"),
            aidlConnection,
            Service.BIND_AUTO_CREATE
        )
    }

    fun onOperateAidl1(view: View) {
        Log.e(TAG, "onOperateAidl1() currentThread is ${Thread.currentThread().name}")
        Log.e(TAG, "onOperateAidl1() aidlStub.getName() is ${stub?.getName("铁木真")}")
    }

    fun onOperateAidl2(view: View) {
        Thread {
            Log.e(TAG, "onOperateAidl2() currentThread is ${Thread.currentThread().name}")
            Log.e(TAG, "aidlStub.getName() is ${stub?.getUserById(233)}")
        }.start()
    }
}
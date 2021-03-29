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


class IpcActivity : AppCompatActivity() {

    companion object {
        val TAG = IpcActivity::class.simpleName
    }

    private var clientMessenger: Messenger? = null
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

    private val messengerConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            try {
                clientMessenger = Messenger(service)
                val message = Message.obtain()
                message.replyTo = Messenger(handler)
                message.what = 1
                clientMessenger?.send(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityIpcLayoutBinding.inflate(layoutInflater).root)
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
            val message = Message()
            message.what = 2
            val bundle = Bundle()
            bundle.putString("name", "Tony")
            message.data = bundle
            //使用
            clientMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun onStartAidl(view: View) {

    }

    fun onAidlSend1(view: View) {

    }

}
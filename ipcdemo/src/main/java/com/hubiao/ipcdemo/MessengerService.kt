package com.hubiao.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class MessengerService : Service() {

    companion object {
        val TAG = MessengerService::class.simpleName
    }

    private var clientMessenger: Messenger? = null
    private var serverMessenger: Messenger? = null

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            Log.e(TAG, "handler() $msg.what")

            when (msg.what) {
                1 -> {
                    clientMessenger = msg.replyTo
                }
                2 -> {
                    try {
                        clientMessenger?.apply {
                            val bundle = Bundle()
                            bundle.putString("name", "messenger is ${msg.data.getString("name")}")
                            val message = Message()
                            message.what = 2
                            message.data = bundle
                            this.send(message)
                        }
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                        Log.e(TAG, "handler() $msg.what $e")
                    }
                }
                else -> {
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        if (serverMessenger == null) {
            serverMessenger = Messenger(handler)
        }

        return serverMessenger?.binder
    }
}
package com.hubiao.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AidlService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
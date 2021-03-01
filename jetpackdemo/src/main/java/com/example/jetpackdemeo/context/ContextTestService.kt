package com.example.jetpackdemeo.context

import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jetpackdemeo.ShowActivity

class ContextTestService : Service() {
    private val binder = ContextTestServiceBinder(this)

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    private fun startActivityFunction1() {
        startActivity(Intent(this@ContextTestService, ShowActivity::class.java))
    }

    private fun startActivityFunction2() {
        startActivity(
            Intent(
                this@ContextTestService,
                ShowActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    private fun showDialogFunction() {
        AlertDialog.Builder(this@ContextTestService)
            .setTitle("Application")
            .setMessage("Application Show Dialog")
            .setCancelable(true)
            .setPositiveButton("确认") { dialogInterface: DialogInterface, i: Int ->
            }
            .setNegativeButton("取消") { dialogInterface: DialogInterface, i: Int ->
            }
            .create()
            .show()
    }

    private fun showToastFunction() {
        Toast.makeText(this@ContextTestService, "Service", Toast.LENGTH_SHORT).show()
    }

    class ContextTestServiceBinder(val service: ContextTestService) : Binder() {
        fun startActivityFunction1() {
            service.startActivityFunction1()
        }

        fun startActivityFunction2() {
            service.startActivityFunction2()
        }

        fun showDialogFunction() {
            service.showDialogFunction()
        }

        fun showToastFunction() {
            service.showToastFunction()
        }
    }
}
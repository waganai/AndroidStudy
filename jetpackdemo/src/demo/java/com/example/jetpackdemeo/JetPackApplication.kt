package com.example.jetpackdemeo

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.lang.Exception

class JetPackApplication : Application() {

    companion object {
        var instance: JetPackApplication? = null
        val TAG = JetPackApplication::class.simpleName
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        Log.e(TAG, "attachBaseContext()")
    }

    override fun onCreate() {
        super.onCreate()

        Log.e(TAG, "Application.onCreate() start")

        if (instance == null) {
            instance = this
        }

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
//                .detectDiskReads()
//                .detectDiskWrites()
                .detectCustomSlowCalls()
                .penaltyDialog()
                .build()
        )

        Log.e(TAG, "Application.onCreate() end")
    }

    fun startActivityFunction1() {
        try {
            startActivity(Intent(this@JetPackApplication, ShowActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun startActivityFunction2() {
        startActivity(
            Intent(this@JetPackApplication, ShowActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun showDialogFunction() {
        AlertDialog.Builder(this@JetPackApplication)
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

    fun showDialogFunction2() {
        val dialog = Dialog(this@JetPackApplication)
        dialog.setTitle("Application Dialog")
        dialog.show()
    }

    fun showToastFunction() {
        Toast.makeText(this@JetPackApplication, "Application", Toast.LENGTH_SHORT).show()
    }
}
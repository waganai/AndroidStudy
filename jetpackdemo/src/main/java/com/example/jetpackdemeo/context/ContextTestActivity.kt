package com.example.jetpackdemeo.context

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.JetPackApplication
import com.example.jetpackdemeo.ShowActivity
import com.example.jetpackdemeo.databinding.ActivityContextTestLayoutBinding

class ContextTestActivity : AppCompatActivity() {

    companion object{
        val TAG = ContextTestActivity::class.simpleName
    }

    var viewBinding: ActivityContextTestLayoutBinding? = null
    var contextServiceBinder: ContextTestService.ContextTestServiceBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityContextTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initListener()

        bindContextTestService()
    }

    private fun bindContextTestService() {
        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.e(TAG, "onServiceConnected() service = ${service.toString()}")
                contextServiceBinder = service as? ContextTestService.ContextTestServiceBinder

                Log.e(TAG, "onServiceConnected() contextServiceBinder = ${contextServiceBinder}")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }

        bindService(Intent(this@ContextTestActivity, ContextTestService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    private fun initListener() {
        viewBinding?.apply {
            btnActivityActivity.setOnClickListener {
                startActivity(Intent(this@ContextTestActivity, ShowActivity::class.java))
            }

            btnApplicationActivity1.setOnClickListener {
                JetPackApplication.instance?.startActivityFunction1()
            }

            btnApplicationActivity2.setOnClickListener {
                JetPackApplication.instance?.startActivityFunction2()
            }

            btnServiceActivity1.setOnClickListener {
                contextServiceBinder?.startActivityFunction1()
            }

            btnServiceActivity2.setOnClickListener {
                contextServiceBinder?.startActivityFunction2()
            }

            btnActivityDialog.setOnClickListener {
                AlertDialog.Builder(this@ContextTestActivity)
                    .setTitle("Activity")
                    .setMessage("Activity Show Dialog")
                    .setCancelable(true)
                    .setPositiveButton("确认") { dialogInterface: DialogInterface, i: Int ->
                    }
                    .setNegativeButton("取消") { dialogInterface: DialogInterface, i: Int ->
                    }
                    .create()
                    .show()
            }

            btnApplicationDialog.setOnClickListener {
                JetPackApplication.instance?.showDialogFunction()
            }

            btnServiceDialog.setOnClickListener {
                contextServiceBinder?.showDialogFunction()
            }

            btnActivityToast.setOnClickListener {
                Toast.makeText(this@ContextTestActivity, "Activity", Toast.LENGTH_SHORT).show()
            }

            btnApplicationToast.setOnClickListener {
                JetPackApplication.instance?.showToastFunction()
            }

            btnServiceToast.setOnClickListener {
                contextServiceBinder?.showToastFunction()
            }
        }
    }
}
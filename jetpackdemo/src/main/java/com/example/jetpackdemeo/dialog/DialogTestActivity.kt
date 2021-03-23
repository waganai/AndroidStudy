package com.example.jetpackdemeo.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityDialogTestLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogTestActivity : AppCompatActivity() {

    var viewBindings: ActivityDialogTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityDialogTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {

    }

    public fun onShowAlertDialog(view: View) {
        MaterialAlertDialogBuilder(this@DialogTestActivity)
            .setTitle("AlertDialog")
            .setNegativeButton("取消") { _: DialogInterface, _: Int ->
                Toast.makeText(this@DialogTestActivity, "AlertDialog: 取消", Toast.LENGTH_LONG).show()
            }
            .setPositiveButton("确认") { _: DialogInterface, _: Int ->
                Toast.makeText(this@DialogTestActivity, "AlertDialog: 确认", Toast.LENGTH_LONG).show()
            }.show()
    }


    public fun onShowDialog(view: View) {
        var dialog = Dialog(this)
        dialog.show()
    }

}
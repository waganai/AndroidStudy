package com.hubiao.study

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.hubiao.base.JETPACK_ACTIVITY
import com.hubiao.base.LAYOUT_ACTIVITY

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onStartJetPack(view: View) {
        Log.e(TAG, "onStartJetPack()")

        ARouter.getInstance().build(JETPACK_ACTIVITY).navigation(this)
    }

    fun onStartView(view: View) {
        Log.e(TAG, "onStartView()")

        ARouter.getInstance().build(LAYOUT_ACTIVITY).navigation(this)
    }
}
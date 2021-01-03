package com.hubiao.view.module.recyclerview

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.R

class RecyclerViewActivity : AppCompatActivity() {

    private var mRecyclerViewFragment = RecyclerViewFragment.buildFragment()
    private var mDividerItemDecorationRecyclerViewFragment:
            DividerItemDecorationRecyclerViewFragment? = null
    private var mSimpleItemDecorationRecyclerViewFragment:
            SimpleItemDecorationRecyclerViewFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recyclerview_layout)

        init()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> if (supportFragmentManager.findFragmentByTag(
                    RecyclerViewFragment::class.java.simpleName
                ) == null
            ) {
                return showRecyclerViewFragment()

            } else {
                super.onKeyDown(keyCode, event)
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        showRecyclerViewFragment()
    }

    private fun initListener() {
        mRecyclerViewFragment?.setRecyclerViewFragmentListener(
            object : RecyclerViewFragment.RecyclerViewFragmentListener {
                override fun onShowDividerItemDecorationFragment() {
                    showDividerItemDecorationFragment()
                }

                override fun onShowSimpleItemDecorationFragment() {
                    showSimpleItemDecorationFragment()
                }
            }
        )
    }

    private fun showRecyclerViewFragment(): Boolean {
        val fragmentBeginTransaction = supportFragmentManager.beginTransaction()
        fragmentBeginTransaction.replace(

            R.id.fl_container,
            mRecyclerViewFragment,
            RecyclerViewFragment::class.java.simpleName
        )
        fragmentBeginTransaction.commit()

        return true
    }

    private fun showDividerItemDecorationFragment() {
        if (mDividerItemDecorationRecyclerViewFragment == null) {
            mDividerItemDecorationRecyclerViewFragment =
                DividerItemDecorationRecyclerViewFragment.buildFragment()
        }

        mDividerItemDecorationRecyclerViewFragment?.let {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fl_container,
                it,
                DividerItemDecorationRecyclerViewFragment::class.java.simpleName
            )
            fragmentTransaction.commit()
        }
    }

    private fun showSimpleItemDecorationFragment() {
        if (mSimpleItemDecorationRecyclerViewFragment == null) {
            mSimpleItemDecorationRecyclerViewFragment =
                SimpleItemDecorationRecyclerViewFragment.buildFragment()
        }

        mSimpleItemDecorationRecyclerViewFragment?.let {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fl_container,
                it,
                SimpleItemDecorationRecyclerViewFragment::class.java.simpleName
            )
            fragmentTransaction.commit()
        }
    }
}
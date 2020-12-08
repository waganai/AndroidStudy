package com.hubiao.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

class ConstraintLayoutActivity : AppCompatActivity() {

    companion object {
        val TAG: String = ConstraintLayoutActivity::class.java.simpleName
    }

    private val mConstraintLayoutFragment = ConstraintLayoutFragment()
    private var mLayout1Fragment: OnlyUIFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_constraintlayout_layout)

        initUI()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (supportFragmentManager.findFragmentByTag("ConstraintLayout") != null) {
                    super.onKeyDown(keyCode, event)
                } else {
                    backToOriginalFragment()
                    false
                }
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    private fun initUI() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container, mConstraintLayoutFragment, "ConstraintLayout")
        transaction.commit()

        mConstraintLayoutFragment.setConstraintLayoutFragmentListener(object :
            ConstraintLayoutFragment.ConstraintLayoutFragmentListener {
            override fun onShowLayout1() {
                showLayout1()
            }
        })
    }

    private fun showLayout1() {
        if (mLayout1Fragment == null) {
            mLayout1Fragment = OnlyUIFragment.buildOnlyUIFragment(0)
        }

        mLayout1Fragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun backToOriginalFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, mConstraintLayoutFragment, "ConstraintLayout")
        transaction.commit()
    }
}
package com.hubiao.view.module.cardview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.R

class CardViewActivity : AppCompatActivity() {

    var mCardViewFragment: CardViewFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cardview_layout)

        init()
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        mCardViewFragment = CardViewFragment.buildFragment()

        mCardViewFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, it, "")
            transaction.commit()
        }
    }

    private fun initListener() {

    }
}
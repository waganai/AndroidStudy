package com.example.jetpackdemeo.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityFragmentAddremoveTestLayoutBinding

class FragmentAddRemoveTestActivity : AppCompatActivity() {

    var viewBinding: ActivityFragmentAddremoveTestLayoutBinding? = null
    var state:Int = 3
    var fragment:LogFragment = LogFragment.createInstance("AddRemoveLogFragment")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityFragmentAddremoveTestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        initListener()
    }

    private fun initListener() {
        viewBinding?.apply {
            btnAdd.setOnClickListener {
                if(state == 3) {
                    state = 0

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(flFragment.id, fragment, LogFragment::class.simpleName)
                    transaction.commit()
                } else {
                    Toast.makeText(this@FragmentAddRemoveTestActivity, "非法", Toast.LENGTH_SHORT).show()
                }
            }

            btnShow.setOnClickListener {
                if(state == 0 || state == 2) {
                    state = 1

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.show(fragment)
                    transaction.commit()
                } else {
                    Toast.makeText(this@FragmentAddRemoveTestActivity, "非法", Toast.LENGTH_SHORT).show()
                }
            }

            btnHide.setOnClickListener {
                if(state == 0|| state == 1) {
                    state = 2

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.hide(fragment)
                    transaction.commit()
                } else {
                    Toast.makeText(this@FragmentAddRemoveTestActivity, "非法", Toast.LENGTH_SHORT).show()
                }
            }

            btnRemove.setOnClickListener {
                if(state == 0|| state == 1 || state == 2) {
                    state = 3

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.remove(fragment)
                    transaction.commit()
                } else {
                    Toast.makeText(this@FragmentAddRemoveTestActivity, "非法", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
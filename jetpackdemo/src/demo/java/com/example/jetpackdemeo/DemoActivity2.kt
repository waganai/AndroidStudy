package com.example.jetpackdemeo

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jetpackdemeo.databinding.ActivityDemoLayoutBinding

class DemoActivity2 : AppCompatActivity() {

    private var viewBinding: ActivityDemoLayoutBinding? = null;

    companion object {
        private val TAG = DemoActivity2::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDemoLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (viewBinding?.vpFragment?.currentItem != 0) {
                    viewBinding?.vpFragment?.currentItem = 0
                    return true
                }
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun init() {
        viewBinding?.apply {
            vpFragment.adapter = object : FragmentPagerAdapter(
                supportFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                val fragments = mutableMapOf<Int, Fragment>()

                override fun getCount(): Int {
                    return 7
                }

                override fun getItem(position: Int): Fragment {
                    Log.e(TAG, "getItem($position)")

                    return fragments[position] ?: when (position) {
                        0 -> {
                            val fragment = MainDemoFragment.newInstance().setSwitchListener(
                                object : MainDemoFragment.SwitchMainDemoFragmentPageListener {
                                    override fun onSwitchFragmentPage(page: Int) {
                                        //
                                        Log.e(TAG, "onSwitchFragmentPage($position)")
                                        //
                                        vpFragment.currentItem = if (page in 1..6) {
                                            page
                                        } else {
                                            0
                                        }
                                    }
                                })
                            fragments[0] = fragment
                            fragment
                        }
                        1 -> {
                            val fragment = PageDemoFragment.newInstance()
                            fragments[1] = fragment
                            fragment
                        }
                        2 -> {
                            val fragment = ViewDemoFragment.newInstance()
                            fragments[2] = fragment
                            fragment
                        }
                        3 -> {
                            val fragment = ThirdpartyDemoFragment.newInstance()
                            fragments[3] = fragment
                            fragment
                        }
                        4 -> {
                            val fragment = ThreadDemoFragment.newInstance()
                            fragments[4] = fragment
                            fragment
                        }
                        5 -> {
                            val fragment = ProcessDemoFragment.newInstance()
                            fragments[5] = fragment
                            fragment
                        }
                        6 -> {
                            val fragment = TestDemoFragment.newInstance()
                            fragments[6] = fragment
                            fragment
                        }
                        else -> {
                            val fragment = MainDemoFragment.newInstance()
                            fragments[0] = fragment
                            fragment
                        }
                    }
                }
            }

            vpFragment.currentItem = 1
        }
    }
}
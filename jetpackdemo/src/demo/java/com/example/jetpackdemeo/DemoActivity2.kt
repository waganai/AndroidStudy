package com.example.jetpackdemeo

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.ActivityDemoLayoutBinding

class DemoActivity2 : AppCompatActivity() {

    private var viewBinding: ActivityDemoLayoutBinding? = null
    private val mFragments = mutableMapOf<Int, Fragment>()
    private var mCurrentFragmentPageIndex = 0

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
                if (getCurrentFragmentPageIndex() != 0) {
                    replaceFragment(0)
                    return true
                }
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun init() {
        replaceFragment(0)
    }

    private fun replaceFragment(pageIndex: Int) {
        if (pageIndex >= 0) {
            viewBinding?.apply {
                supportFragmentManager.beginTransaction()
                    .replace(clFragmentContainer.id, getFragment(pageIndex)).commit()
            }
        }
    }

    private fun getCurrentFragmentPageIndex(): Int {
        return mCurrentFragmentPageIndex
    }

    private fun getFragment(pageIndex: Int): Fragment {
        mCurrentFragmentPageIndex = pageIndex
        if (mCurrentFragmentPageIndex > 6) {
            mCurrentFragmentPageIndex = 0
        }

        return mFragments[pageIndex] ?: when (pageIndex) {
            0 -> {
                val fragment = MainDemoFragment.newInstance().setSwitchListener(
                    object : MainDemoFragment.SwitchMainDemoFragmentPageListener {
                        override fun onSwitchFragmentPage(page: Int) {
                            //
                            Log.e(TAG, "onSwitchFragmentPage($pageIndex)")
                            //
                            replaceFragment(
                                if (page in 1..6) {
                                    page
                                } else {
                                    0
                                }
                            )
                        }
                    })
                mFragments[0] = fragment
                fragment
            }
            1 -> {
                val fragment = PageDemoFragment.newInstance()
                mFragments[1] = fragment
                fragment
            }
            2 -> {
                val fragment = ViewDemoFragment.newInstance()
                mFragments[2] = fragment
                fragment
            }
            3 -> {
                val fragment = ThirdpartyDemoFragment.newInstance()
                mFragments[3] = fragment
                fragment
            }
            4 -> {
                val fragment = ThreadDemoFragment.newInstance()
                mFragments[4] = fragment
                fragment
            }
            5 -> {
                val fragment = ProcessDemoFragment.newInstance()
                mFragments[5] = fragment
                fragment
            }
            6 -> {
                val fragment = TestDemoFragment.newInstance()
                mFragments[6] = fragment
                fragment
            }
            else -> {
                val fragment = MainDemoFragment.newInstance()
                mFragments[0] = fragment
                fragment
            }
        }
    }
}
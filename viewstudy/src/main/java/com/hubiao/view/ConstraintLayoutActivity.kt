package com.hubiao.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

class ConstraintLayoutActivity : AppCompatActivity() {

    companion object {
        val TAG: String = ConstraintLayoutActivity::class.java.simpleName
    }

    private val mConstraintLayoutFragment = ConstraintLayoutFragment()
    private var mRelativePositionFragment: OnlyUIFragment? = null
    private var mMarginFragment: OnlyUIFragment? = null
    private var mCenteringPositionFragment: OnlyUIFragment? = null
    private var mCircularPositionFragment: OnlyUIFragment? = null
    private var mVisibilityBehaviorFragment: OnlyUIFragment? = null
    private var mDimensionConstraintFragment: OnlyUIFragment? = null
    private var mChainsFragment: OnlyUIFragment? = null
    private var mVirtualHelperFragment: OnlyUIFragment? = null
    private var mOptimizerFragment: OnlyUIFragment? = null

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
            override fun onShowRelativePosition() {
                showRelativePosition()
            }

            override fun onShowMargin() {
                showMargin()
            }

            override fun onShowCenteringPosition() {
                showCenteringPosition()
            }

            override fun onShowCircularPosition() {
                showCircularPosition()
            }

            override fun onShowVisibilityBehavior() {
                showVisibilityBehavior()
            }

            override fun onShowDimensionConstraint() {
                showDimensionConstraint()
            }

            override fun onShowChains() {
                showChains()
            }

            override fun onShowVirtualHelpersObjects() {
                showVirtualHelpersObjects()
            }

            override fun onShowOptimizer() {
                showOptimizer()
            }
        })
    }

    private fun showRelativePosition() {
        if (mRelativePositionFragment == null) {
            mRelativePositionFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }

        mRelativePositionFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showMargin() {
        if (mMarginFragment == null) {
            mMarginFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }

        mMarginFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showCenteringPosition() {
        if (mCenteringPositionFragment == null) {
            mCenteringPositionFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }

        mCenteringPositionFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showCircularPosition() {
        if (mCircularPositionFragment == null) {
            mCircularPositionFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mCircularPositionFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showVisibilityBehavior() {
        if (mVisibilityBehaviorFragment == null) {
            mVisibilityBehaviorFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mVisibilityBehaviorFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showDimensionConstraint() {
        if (mDimensionConstraintFragment == null) {
            mDimensionConstraintFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mDimensionConstraintFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showChains() {
        if (mChainsFragment == null) {
            mChainsFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mChainsFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showVirtualHelpersObjects() {
        if (mVirtualHelperFragment == null) {
            mVirtualHelperFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mVirtualHelperFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Layout1")
            transaction.commit()
        }
    }

    private fun showOptimizer() {
        if (mOptimizerFragment == null) {
            mOptimizerFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_relative_positioning)
        }
        mOptimizerFragment?.let {
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
package com.hubiao.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.hubiao.view.constraintlayout.ConstraintLayoutVisibilityBehaviorFragment

class ConstraintLayoutActivity : AppCompatActivity() {

    companion object {
        val TAG: String = ConstraintLayoutActivity::class.java.simpleName
    }

    private val mConstraintLayoutFragment = ConstraintLayoutFragment()
    private var mRelativePositionFragment: OnlyUIFragment? = null
    private var mMarginFragment: OnlyUIFragment? = null
    private var mCenteringPositionFragment: OnlyUIFragment? = null
    private var mCircularPositionFragment: OnlyUIFragment? = null
    private var mVisibilityBehaviorFragment: ConstraintLayoutVisibilityBehaviorFragment? = null
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

            transaction.replace(R.id.fl_container, it, "RelativePosition")
            transaction.commit()
        }
    }

    private fun showMargin() {
        if (mMarginFragment == null) {
            mMarginFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_margin)
        }

        mMarginFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Margin")
            transaction.commit()
        }
    }

    private fun showCenteringPosition() {
        if (mCenteringPositionFragment == null) {
            mCenteringPositionFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_centering_position)
        }

        mCenteringPositionFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "CenteringPosition")
            transaction.commit()
        }
    }

    private fun showCircularPosition() {
        if (mCircularPositionFragment == null) {
            mCircularPositionFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_circular_position)
        }
        mCircularPositionFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "CircularPosition")
            transaction.commit()
        }
    }

    private fun showVisibilityBehavior() {
        if (mVisibilityBehaviorFragment == null) {
            mVisibilityBehaviorFragment =
                ConstraintLayoutVisibilityBehaviorFragment.buildFragment()
        }
        mVisibilityBehaviorFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "VisibilityBehavior")
            transaction.commit()
        }
    }

    private fun showDimensionConstraint() {
        if (mDimensionConstraintFragment == null) {
            mDimensionConstraintFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_dimension_constraint)
        }
        mDimensionConstraintFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "DimensionConstraint")
            transaction.commit()
        }
    }

    private fun showChains() {
        if (mChainsFragment == null) {
            mChainsFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_chains)
        }
        mChainsFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Chains")
            transaction.commit()
        }
    }

    private fun showVirtualHelpersObjects() {
        if (mVirtualHelperFragment == null) {
            mVirtualHelperFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_virtual_helpers_objects)
        }
        mVirtualHelperFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "VirtualHelpersObjects")
            transaction.commit()
        }
    }

    private fun showOptimizer() {
        if (mOptimizerFragment == null) {
            mOptimizerFragment =
                OnlyUIFragment.buildOnlyUIFragment(R.layout.fragment_layout_constraintlayout_optimizer)
        }
        mOptimizerFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fl_container, it, "Optimizer")
            transaction.commit()
        }
    }

    private fun backToOriginalFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, mConstraintLayoutFragment, "ConstraintLayout")
        transaction.commit()
    }
}
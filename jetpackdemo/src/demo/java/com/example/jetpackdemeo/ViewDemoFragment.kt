package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentViewDemoLayoutBinding
import com.example.jetpackdemeo.view.ViewUiTestActivity
import com.example.jetpackdemeo.view.cordinatorlayout.CoordinatorLayoutTestFragment
import com.example.jetpackdemeo.view.cordinatorlayout.CoordinatorLayoutTestFragment2
import com.example.jetpackdemeo.view.viewstub.ViewStubTestFragment

class ViewDemoFragment() : Fragment() {
    companion object {
        val TAG = ViewDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): ViewDemoFragment {
            return ViewDemoFragment()
        }
    }

    private var viewBinding: FragmentViewDemoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentViewDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        viewBinding?.apply {
            btnCustomview.setOnClickListener {
                startActivity(Intent(activity, ViewUiTestActivity::class.java))
            }

            btnViewstub.setOnClickListener {
                startActivity(
                    Intent(activity, FragmentLoadActivity::class.java)
                        .putExtra(
                            FragmentLoadActivity.FRAGMENT_CLASS,
                            ViewStubTestFragment::class.qualifiedName
                        )
                )
            }

            btnCoordinatorlayout.setOnClickListener {
                startActivity(
                    Intent(activity, FragmentLoadActivity::class.java)
                        .putExtra(
                            FragmentLoadActivity.FRAGMENT_CLASS,
                            CoordinatorLayoutTestFragment::class.qualifiedName
                        )
                )
            }

            //
            //
            //
            btnCoordinatorlayout2.setOnClickListener {
                startActivity(
                    Intent(activity, FragmentLoadActivity::class.java)
                        .putExtra(
                            FragmentLoadActivity.FRAGMENT_CLASS,
                            CoordinatorLayoutTestFragment2::class.qualifiedName
                        )
                )
            }
        }
    }
}
package com.example.jetpackdemeo.view.cordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentCoordinatorlayoutTestLayoutBinding
import com.google.android.material.snackbar.Snackbar

class CoordinatorLayoutTestFragment: Fragment() {
    companion object{
        private val TAG = CoordinatorLayoutTestFragment::class.simpleName
    }

    private var viewBinding :FragmentCoordinatorlayoutTestLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCoordinatorlayoutTestLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            fbCor.setOnClickListener {
                Snackbar.make(scrollView, "Snackbar", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
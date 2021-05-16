package com.example.jetpackdemeo.view.viewstub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentViewstubTestLayoutBinding
import java.lang.Exception

class ViewStubTestFragment : Fragment() {

    companion object {
        val TAG = ViewStubTestFragment::class.simpleName
    }

    private var viewBinding: FragmentViewstubTestLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentViewstubTestLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            try {
                vb2.inflate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            btnVbShow1.setOnClickListener {
                try {
                    idVsClViewstubTest1.inflate()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
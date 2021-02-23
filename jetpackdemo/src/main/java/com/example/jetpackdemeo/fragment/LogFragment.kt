package com.example.jetpackdemeo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentLogLayoutBinding

class LogFragment(arg1: String) : Fragment() {

    private var mLogFragmentTag = "LogFragment"
    private var viewBinding: FragmentLogLayoutBinding? = null

    companion object {
        fun createInstance(name: String): LogFragment {
            return LogFragment(name)
        }
    }

    init {
        mLogFragmentTag = arg1
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.e(mLogFragmentTag, "onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(mLogFragmentTag, "onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(mLogFragmentTag, "onCreateView()")

        viewBinding = FragmentLogLayoutBinding.inflate(inflater, container, false)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(mLogFragmentTag, "onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e(mLogFragmentTag, "onActivityCreated()")
    }

    override fun onStart() {
        super.onStart()

        Log.e(mLogFragmentTag, "onStart()")

        viewBinding?.tvName?.text = mLogFragmentTag
    }

    override fun onResume() {
        super.onResume()

        Log.e(mLogFragmentTag, "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.e(mLogFragmentTag, "onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.e(mLogFragmentTag, "onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.e(mLogFragmentTag, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(mLogFragmentTag, "onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()

        Log.e(mLogFragmentTag, "onDetach()")
    }
}
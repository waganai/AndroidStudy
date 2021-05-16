package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentProcessDemoLayoutBinding
import com.example.jetpackdemeo.ipc.IpcActivity

class ProcessDemoFragment : Fragment() {
    companion object {
        val TAG = ProcessDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): ProcessDemoFragment {
            return ProcessDemoFragment()
        }
    }

    private var viewBinding: FragmentProcessDemoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProcessDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnIpc.setOnClickListener {
                startActivity(Intent(activity, IpcActivity::class.java))
            }
        }
    }
}
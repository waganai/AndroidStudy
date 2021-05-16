package com.example.jetpackdemeo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.FragmentThirdpartyDemoLayoutBinding
import com.example.jetpackdemeo.thirdframework.glide.GlideTestActivity
import com.example.jetpackdemeo.thirdframework.okhttp.OkHttpTestActivity
import com.example.jetpackdemeo.thirdframework.retrofit.RetrofitTestActivity
import com.example.jetpackdemeo.thirdframework.rxjava.RxJavaTestActivity

class ThirdpartyDemoFragment : Fragment() {
    companion object {
        val TAG = ThirdpartyDemoFragment::class.simpleName

        @JvmStatic
        fun newInstance(): ThirdpartyDemoFragment {
            return ThirdpartyDemoFragment()
        }
    }

    private var viewBinding: FragmentThirdpartyDemoLayoutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentThirdpartyDemoLayoutBinding.inflate(layoutInflater)

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewBinding?.apply {
            btnGlide.setOnClickListener {
                startActivity(Intent(activity, GlideTestActivity::class.java))
            }

            btnOkhttp.setOnClickListener {
                startActivity(Intent(activity, OkHttpTestActivity::class.java))
            }

            btnRetrofit.setOnClickListener {
                startActivity(Intent(activity, RetrofitTestActivity::class.java))
            }

            btnRxjava.setOnClickListener {
                startActivity(Intent(activity, RxJavaTestActivity::class.java))
            }
        }
    }
}
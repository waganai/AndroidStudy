package com.example.jetpackdemeo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemeo.databinding.FragmentViewmodelLayoutBinding

class ViewModelFragment : Fragment() {

    lateinit var viewBinding: FragmentViewmodelLayoutBinding
    private lateinit var valueModel: ValueModel

    private lateinit var sp: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentViewmodelLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        sp = requireActivity().getPreferences(Context.MODE_PRIVATE)

        valueModel = ViewModelProvider(
            requireActivity(),
            ValueModel
                .ValueModelFactory(
                    sp.getInt(
                        "REVERSED_VALUE",
                        0
                    )
                )
        )
            .get(ValueModel::class.java)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    override fun onPause() {
        super.onPause()

        sp.edit {
            putInt("REVERSED_VALUE", valueModel.value)
        }
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
        viewBinding.tvValue.text = valueModel.value.toString()
    }

    private fun initListener() {
        viewBinding.btnAdd.setOnClickListener {
            valueModel.value++

            viewBinding.tvValue.text = valueModel.value.toString()
        }

        viewBinding.btnReduce.setOnClickListener {
            valueModel.value--

            viewBinding.tvValue.text = valueModel.value.toString()
        }
    }
}
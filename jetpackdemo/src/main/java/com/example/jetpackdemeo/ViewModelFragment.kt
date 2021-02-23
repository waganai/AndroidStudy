package com.example.jetpackdemeo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemeo.databinding.FragmentViewmodelLayoutBinding
import com.example.jetpackdemeo.fragment.FragmentTestActivity1
import com.example.jetpackdemeo.fragment.FragmentTestActivity2
import com.example.jetpackdemeo.lifeobserver.SimpleActivityLifeObserver
import com.example.jetpackdemeo.viewmodel.CounterModel
import com.example.jetpackdemeo.viewmodel.UserModel

class ViewModelFragment : Fragment() {

    lateinit var viewBinding: FragmentViewmodelLayoutBinding
    private lateinit var counterModel: CounterModel
    private lateinit var userModel: UserModel

    private lateinit var sp: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycle.addObserver(SimpleActivityLifeObserver())

        viewBinding = FragmentViewmodelLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        sp = requireActivity().getPreferences(Context.MODE_PRIVATE)

        counterModel = ViewModelProvider(
            requireActivity(),
            CounterModel
                .CounterModelFactory(
                    sp.getInt(
                        "REVERSED_COUNTER",
                        0
                    )
                )
        )
            .get(CounterModel::class.java)

        userModel = ViewModelProvider(
            requireActivity(),
            UserModel.UserModelFactory("小", "明", 0)
        )
            .get(UserModel::class.java)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    override fun onPause() {
        super.onPause()

        sp.edit {
            putInt("REVERSED_COUNTER", counterModel.counter.value ?: 0)
        }
    }

    private fun init() {
        initView()

        initListener()
    }

    private fun initView() {
    }

    private fun initListener() {
        counterModel.counter.observe(requireActivity(), Observer {
            viewBinding.tvValue.text = it.toString()
        })

        userModel.userName.observe(requireActivity(), {
            viewBinding.tvName.text = it
        })

        userModel.user.observe(requireActivity(), Observer {
            viewBinding.tvName.text = it.firstName + it.lastName
        })


        viewBinding.apply {
            btnPlusOne.setOnClickListener {
                counterModel.plusOne()
            }

            btnReduceOne.setOnClickListener {
                counterModel.reduceOne()
            }

            btnClear.setOnClickListener {
                counterModel.clear()
            }

            btnChangeUser.setOnClickListener {
                userModel.getUser((0..1000).random().toString())
            }

            btnCurrentState.setOnClickListener {
                Toast
                    .makeText(
                        requireContext(),
                        "${lifecycle.currentState}",
                        Toast.LENGTH_LONG
                    )
                    .show()
            }

            btnFragmentActivity1.setOnClickListener {
                activity?.startActivity(Intent(context, FragmentTestActivity1::class.java))
            }

            btnFragmentActivity2.setOnClickListener {
                activity?.startActivity(Intent(context, FragmentTestActivity2::class.java))
            }
        }
    }
}
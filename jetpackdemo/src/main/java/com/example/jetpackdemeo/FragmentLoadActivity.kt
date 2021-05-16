package com.example.jetpackdemeo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackdemeo.databinding.ActivityLoadFragmentLayoutBinding
import java.lang.Exception

class FragmentLoadActivity : AppCompatActivity() {

    companion object {
        val TAG = FragmentLoadActivity::class.simpleName

        const val FRAGMENT_CLASS = "FRAGMENT_CLASS"
    }

    private var viewBinding: ActivityLoadFragmentLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityLoadFragmentLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    private fun init() {
        val fragmentClassPath = intent.getStringExtra(FRAGMENT_CLASS)?:""

        viewBinding?.apply {
            getFragmentInstance(fragmentClassPath)?.apply {
                supportFragmentManager.beginTransaction().replace(flLoadFragment.id, this)
                    .commit()
            }
        }
    }

    private fun getFragmentInstance(classPath: String): Fragment? {
        Log.e(TAG, "getFragmentInstance($classPath)")

        return try {
            val clazz = Class.forName(classPath)
            val instance = clazz.getConstructor().newInstance()

            if (instance is Fragment) instance else null
        } catch (e: Exception) {
            null
        }
    }
}
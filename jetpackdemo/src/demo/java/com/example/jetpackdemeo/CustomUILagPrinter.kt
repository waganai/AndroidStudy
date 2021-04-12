package com.example.jetpackdemeo

import android.util.Log
import android.util.Printer

class CustomUILagPrinter : Printer {
    companion object {
        val TAG = CustomUILagPrinter::class.simpleName
    }

    var mLagThreshold = 17L
    var mStartTime = -1L
    var mEndTime = -1L

    override fun println(x: String?) {
        if (x?.startsWith(">>>>> Dispatching to ", false) == true) {
            mStartTime = System.currentTimeMillis()
            return
        }

        if (x?.startsWith("<<<<< Finished to ", false) == true) {
            mEndTime = System.currentTimeMillis()

            if (mEndTime - mStartTime >= mLagThreshold) {

                Log.e(
                    TAG,
                    "Lag In Thread.${Thread.currentThread().name}, duration = ${mEndTime - mStartTime}"
                )
            }
        }
    }
}
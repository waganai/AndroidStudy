package com.example.jetpackdemeo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ValueModel(valueReversed: Int) : ViewModel() {
    var value: Int = valueReversed

    class ValueModelFactory(private val valueReversed: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ValueModel(valueReversed) as T
        }
    }
}
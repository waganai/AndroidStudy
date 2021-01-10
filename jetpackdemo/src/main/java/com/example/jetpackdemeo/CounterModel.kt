package com.example.jetpackdemeo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CounterModel(counterReversed: Int) : ViewModel() {
    var counter = MutableLiveData<Int>()

    init {
        counter.value = counterReversed
    }

    fun plusOne() {
        val value = counter.value ?: 0
        // 此处不能直接获取counter.value ?: 0加上1赋值，否则额没有变化，原因未知
        counter.postValue(value + 1)
    }

    fun reduceOne() {
        val value = counter.value ?: 0
        counter.postValue(value - 1)
    }

    fun clear() {
        counter.postValue(0)
    }

    class CounterModelFactory(private val counterReversed: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CounterModel(counterReversed) as T
        }
    }
}
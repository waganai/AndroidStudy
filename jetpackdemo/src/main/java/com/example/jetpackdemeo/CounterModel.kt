package com.example.jetpackdemeo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CounterModel(counterReversed: Int) : ViewModel() {
    val counter: LiveData<Int>
        get() = _counter
    private var _counter = MutableLiveData<Int>()

    init {
        _counter.value = counterReversed
    }

    fun plusOne() {
        val value = _counter.value ?: 0
        // 此处不能直接获取counter.value ?: 0加上1赋值，否则额没有变化，原因未知
        _counter.postValue(value + 1)
    }

    fun reduceOne() {
        val value = _counter.value ?: 0
        _counter.postValue(value - 1)
    }

    fun clear() {
        _counter.postValue(0)
    }

    class CounterModelFactory(private val counterReversed: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CounterModel(counterReversed) as T
        }
    }
}
package com.example.myapplication

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingCount: Int): ViewModel() {
    private var count = MutableLiveData<Int>()

    val countData: LiveData<Int>
        get() = count
    val userName = MutableLiveData<String>()

    val input = MutableLiveData<String>()

    init {
        userName.value = "Frank"
        count.value = startingCount
    }

    fun increaseCountByOne() {
        count.value = count.value?.plus(1)
    }


    fun setTotal(){
        val inputValue: Int = input.value!!.toInt()
        count.value = count.value?.plus(inputValue)
    }

}
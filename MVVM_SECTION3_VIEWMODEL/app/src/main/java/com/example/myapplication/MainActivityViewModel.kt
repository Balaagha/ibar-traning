package com.example.myapplication

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingCount: Int): ViewModel() {
    private var count = 0

    init {
        count = startingCount
    }

    fun getCurrentCount(): Int{
        return count
    }

    fun getUpdateCount(): Int{
        return ++count
    }


    fun setTotal(input: Int){
        count += input
    }

}
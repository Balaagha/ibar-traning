package com.example.dagger.daggerclass

import android.util.Log

class MemoryCard {
    init {
        Log.i("infoTag","Memory Card Constructed")
    }

    fun getSpaceAvailability(){
        Log.i("infoTag","Memory space available")
    }
}
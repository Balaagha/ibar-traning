package com.example.dagger.daggerclass

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor () {
    init {
        Log.i("infoTag","Service Provider Constructed")
    }

    fun getServiceProvider(){
        Log.i("infoTag","Service Provider connected")
    }
}


package com.example.dagger.daggerclass

import android.util.Log
import javax.inject.Inject

class SIMCard @Inject constructor (private val serviceProvider: ServiceProvider) {
    init {
        Log.i("infoTag","SIM Card Constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}
package com.example.dagger.daggerclass

import android.util.Log
import javax.inject.Inject

class LithiumBattery @Inject constructor(): Battery {
    init {
        Log.i("infoTag","Lithium Battery Constructed ")
    }
    override fun getPower() {
        Log.i("infoTag","Lithium Battery power is available")
        super.getPower()
    }
}
package com.example.dagger.daggerclass

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.i("infoTag","SmartPhone Constructed")
    }

    fun makeCallWithRecording(){
        Log.i("infoTag","Calling ... (In SmartPhone Class) ")
    }
}

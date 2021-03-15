package com.example.dagger.daggerclass

import android.util.Log

interface Battery {

    fun getPower(){
        Log.i("infoTag","Battery power is available in Interface")
    }
}
package com.example.dagger.daggerclass

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val memorySize: Int ) {
    @Provides
    fun providesMemoryCard(): MemoryCard{
        Log.i("infoTag","Size of the memory is $memorySize at MemoryCardModule ")
        return MemoryCard()
    }
}
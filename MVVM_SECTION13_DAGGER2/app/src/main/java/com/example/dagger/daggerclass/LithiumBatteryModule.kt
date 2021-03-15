package com.example.dagger.daggerclass

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LithiumBatteryModule {

    @Binds
    abstract fun bindsLithiumBattery(lithiumBattery: LithiumBattery): Battery
}

/*
@Module
class LithiumBatteryModule {

    @Provides
    fun providesLithiumBattery(lithiumBattery: LithiumBattery): Battery{
        return lithiumBattery
    }

//    @Provides
//    fun providesLithiumBattery(): Battery{
//        return LithiumBattery()
//    }
}

 */
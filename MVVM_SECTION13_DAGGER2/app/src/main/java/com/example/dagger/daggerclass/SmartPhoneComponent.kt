package com.example.dagger.daggerclass

import com.example.dagger.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MemoryCardModule::class,LithiumBatteryModule::class])
interface SmartPhoneComponent {
//    fun getSmartPhoneComponent() :SmartPhone
    fun inject(mainActivity: MainActivity)
}
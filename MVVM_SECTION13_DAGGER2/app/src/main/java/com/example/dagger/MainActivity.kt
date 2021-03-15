package com.example.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger.MainActivity_MembersInjector.create
import com.example.dagger.daggerclass.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var smartPhone: SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as SmartPhoneApp).smartPhoneComponent.inject(this)
        smartPhone.makeCallWithRecording()
        //without state of module
//        DaggerSmartPhoneComponent.create().inject(this)

        //MemoryCardModule car
//        DaggerSmartPhoneComponent.builder()
//            .memoryCardModule(MemoryCardModule(10))
//            .build()
//            .inject(this)
//
//        smartPhone.makeCallWithRecording()
    // DaggerSmartPhoneComponent.create().getSmartPhoneComponent().makeCallWithRecording()

//        val smartPhone = SmartPhone(Battery(), SIMCard(ServiceProvider()), MemoryCard())
//                .makeCallWithRecording()

    }
}
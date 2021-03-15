package com.example.myapplication.androidjetpackcleanarchitecturetestting.navigation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNavigationIntroBinding

class NavigationIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_navigation_intro)
    }
}
package com.example.myapplication.androidjetpackcleanarchitecturetestting.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.navigation.view.NavigationIntroActivity
import kotlinx.android.synthetic.main.activity_navigation_menu.*

class NavigationMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_menu)

        navigationIntro.setOnClickListener{
            var newIntent = Intent(this@NavigationMenuActivity, NavigationIntroActivity::class.java)
            startActivity(newIntent)
        }
    }
}
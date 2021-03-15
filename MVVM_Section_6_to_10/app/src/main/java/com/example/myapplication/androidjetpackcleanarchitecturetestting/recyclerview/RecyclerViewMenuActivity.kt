package com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.view.RevyvlerViewIntro
import kotlinx.android.synthetic.main.activity_recycler_view_menu.*

class RecyclerViewMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_menu)
        recyclerViewIntro.setOnClickListener{
            var newIntent = Intent(this@RecyclerViewMenuActivity, RevyvlerViewIntro::class.java)
            startActivity(newIntent)
        }
    }
}
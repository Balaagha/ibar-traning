package com.example.myapplication.androidjetpackcleanarchitecturetestting.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.view.RoomIntroActivity
import kotlinx.android.synthetic.main.activity_room_menu.*

class RoomMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_menu)

        roomIntro.setOnClickListener{
            val newIntent = Intent(this@RoomMenuActivity, RoomIntroActivity::class.java)
            startActivity(newIntent)
        }
    }
}
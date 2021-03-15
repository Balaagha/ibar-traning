package com.example.myapplication.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.CoroutineMenuActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.navigation.NavigationMenuActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.RecyclerViewMenuActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.RetrofitMenuActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.RoomMenuActivity
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.coroutineSection
import kotlinx.android.synthetic.main.activity_menu.navigationSection
import kotlinx.android.synthetic.main.activity_menu.recyclerViewSection
import kotlinx.android.synthetic.main.activity_menu.retrofitSection
import kotlinx.android.synthetic.main.activity_menu.roomSection

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        navigationSection.setOnClickListener{
            val newIntent = Intent(this@MenuActivity, NavigationMenuActivity::class.java)
            startActivity(newIntent)
        }
        recyclerViewSection.setOnClickListener{
            val newIntent = Intent(this@MenuActivity, RecyclerViewMenuActivity::class.java)
            startActivity(newIntent)
        }
        coroutineSection.setOnClickListener{
            val newIntent = Intent(this@MenuActivity, CoroutineMenuActivity::class.java)
            startActivity(newIntent)
        }
        roomSection.setOnClickListener{
            val newIntent = Intent(this@MenuActivity, RoomMenuActivity::class.java)
            startActivity(newIntent)
        }
        retrofitSection.setOnClickListener{
            val newIntent = Intent(this@MenuActivity, RetrofitMenuActivity::class.java)
            startActivity(newIntent)
        }
    }
}
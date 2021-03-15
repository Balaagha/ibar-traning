package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.viewmodel.CorountinesWithLiveDataViewModel

class CorountinesWithLiveDataActivity : AppCompatActivity() {
    private lateinit var corountinesWithLiveDataViewModel: CorountinesWithLiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corountines_with_live_data)

        corountinesWithLiveDataViewModel = ViewModelProvider(this).get(CorountinesWithLiveDataViewModel::class.java)

        // First way
//        corountinesWithLiveDataViewModel.getUsers()
        // Second way
//      At the second way we don't need to invoke a function like the first way
        corountinesWithLiveDataViewModel.users.observe(this, Observer { users->
            users.forEach {
                Log.i("myTag","name is ${it.name}")
            }
        })
    }
}
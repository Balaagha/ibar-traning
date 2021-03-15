package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.viewmodel.CoroutineWithViewModel

class CoroutineWithViewModelActivity : AppCompatActivity() {
    private lateinit var coroutineWithViewModel: CoroutineWithViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_with_view_model)
        coroutineWithViewModel = ViewModelProvider(this).get(CoroutineWithViewModel::class.java)
        coroutineWithViewModel.getUserData()
        coroutineWithViewModel.user.observe(this,{users->
            users.forEach{
                Log.i("mytag","name is ${it.name}")
            }
        })
    }
}
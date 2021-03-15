package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(20)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.countData.observe(this, Observer {
            binding.txtCount.text = it.toString()
        })
        binding.apply {
            btnControl.setOnClickListener {
                val textData = input.text?.toString()
                if(!textData.isNullOrEmpty()) {
                    if (textData.toInt() > 0) {
                        viewModel.setTotal(textData.toInt())
                    } else {
                        viewModel.increaseCountByOne()
                    }
                }
            }
        }


    }
}
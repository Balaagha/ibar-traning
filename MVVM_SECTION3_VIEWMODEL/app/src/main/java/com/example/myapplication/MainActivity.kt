package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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

        binding.apply {
            txtCount.text = viewModel.getCurrentCount().toString()
            btnControl.setOnClickListener {
                if(input.text.toString().toInt() > 0){
                    viewModel.setTotal(input.text.toString().toInt())
                    txtCount.text = viewModel.getCurrentCount().toString()
                }else{
                    txtCount.text = viewModel.getUpdateCount().toString()
                }
            }
        }


    }
}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {
            button.setOnClickListener{
                displayGreeting()
            }
            btnControl.setOnClickListener{
                startOrStopProgressBar()
            }
            student = getStudents()
        }


    }

    private fun startOrStopProgressBar() {
        binding.apply{
            if(progressBar.visibility == View.GONE){
                progressBar.visibility = View.VISIBLE
                btnControl.text = "Stop"
            }else{
                progressBar.visibility = View.GONE
                btnControl.text = "Start"
            }
        }
    }

    private fun displayGreeting() {
        binding.apply {
            textView.text = "Hello ${editTextTextPersonName.text}"
        }
    }

    private fun getStudents(): Student {
        return Student(1,"Eldar","eldar@gmail.com")
    }
}
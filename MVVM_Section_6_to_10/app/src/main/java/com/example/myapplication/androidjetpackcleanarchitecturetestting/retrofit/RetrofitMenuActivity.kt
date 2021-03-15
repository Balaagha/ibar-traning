package com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.view.RetrofitIntroActivity
import kotlinx.android.synthetic.main.activity_coroutine_menu.*
import kotlinx.android.synthetic.main.activity_retrofit_menu.*

class RetrofitMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_menu)
        retrofitIntro.setOnClickListener{
            val newIntent = Intent(this@RetrofitMenuActivity, RetrofitIntroActivity::class.java)
            startActivity(newIntent)
        }
    }
}
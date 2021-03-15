package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.coroutinelifecyclescope.CoroutineLifecycleScopeActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view.CorountinesWithLiveDataActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view.CoroutineIntroActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view.CoroutineWithViewModelActivity
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view.CoroutinesWithAsyncAwaitActivity
import kotlinx.android.synthetic.main.activity_coroutine_menu.*

class CoroutineMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_menu)

        coroutineIntro.setOnClickListener{
            val newIntent = Intent(this@CoroutineMenuActivity, CoroutineIntroActivity::class.java)
            startActivity(newIntent)
        }
        coroutineWithAsyncAwait.setOnClickListener{
            val newIntent = Intent(this@CoroutineMenuActivity, CoroutinesWithAsyncAwaitActivity::class.java)
            startActivity(newIntent)
        }
        coroutineWithViewModel.setOnClickListener{
            val newIntent = Intent(this@CoroutineMenuActivity, CoroutineWithViewModelActivity::class.java)
            startActivity(newIntent)
        }
        coroutineLifecycleScopeActivity.setOnClickListener{
            val newIntent = Intent(this@CoroutineMenuActivity, CoroutineLifecycleScopeActivity::class.java)
            startActivity(newIntent)
        }
        coroutineWithLiveData.setOnClickListener{
            val newIntent = Intent(this@CoroutineMenuActivity, CorountinesWithLiveDataActivity::class.java)
            startActivity(newIntent)
        }

    }
}
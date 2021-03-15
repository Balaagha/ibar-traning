package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesWithAsyncAwaitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_with_async_await)
        // Below process is sequential decomposition
        /*CoroutineScope(IO).launch{
            Log.i("mytag","Calculation is started....")
            val stock1 = getStock1()
            val stock2 = getStock2()
            val total = stock1 + stock2
            Log.i("mytag","Total is $total")
        }*/
        // Below process is parallel decomposition => To do that we need to use async coroutine builder. Async and Launch are the coroutine builder. Launch coroutine returns a job. But async coroutine builder returns an instance of Deferred. We can use that deferred value by invoking its await function.
        CoroutineScope(IO).launch{
            Log.i("mytag","Calculation is started....")
            val stock1 = async { getStock1() }
            val stock2 = async { getStock2() }
            val total = stock1.await() + stock2.await()
            Log.i("mytag","Total is $total")
        }

        // Other option for above codes (with ui implementation)
        CoroutineScope(Main).launch{
            Log.i("mytag","Calculation is started....")
            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }
            val total = stock1.await() + stock2.await()
            Toast.makeText(applicationContext,"Total is $total",Toast.LENGTH_LONG).show()
        }
    }
}

private suspend fun getStock1(): Int {
    delay(10000)
    Log.i("mytag","stock 1 returned")
    return 55000
}

private suspend fun getStock2(): Int {
    delay(8000)
    Log.i("mytag","stock 2 returned")
    return 35000
}
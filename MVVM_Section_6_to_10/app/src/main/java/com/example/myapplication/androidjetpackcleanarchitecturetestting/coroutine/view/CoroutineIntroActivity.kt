package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository.UserDataManagerStructuredConcurrency
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository.UserDataManagerUnStructuredConcurrency
import kotlinx.android.synthetic.main.activity_coroutine_intro.*
import kotlinx.coroutines.*

// GlobalScope is used to launch top-level coroutines which are operating on the whole application lifetime.
// CoroutineScope is the interface we have used to provide the scope to our coroutine
// supervisorScope

// Dispatchers.Main -> coroutine run in a ui thread,we only use main dispatchers for small,light weight task like call to a ui function, call to a suspending function or to get updates from the livedata
// Dispatchers.IO -> coroutine run in a background thread. We use this to work with local database, communicate with network and work with files.
// Dispatchers.Default -> Default dispatcher is used for cpu intensive tasks such as sorting a list of data with 10000 list items, or parsing a huge json file with details of 100000 movies.
// Dispatchers.Unconfined -> This is used with GlobalScope. If we use Dispatchers.Unconfined coroutine will run on the current thread, but if is suspended and resumed it will run on whichever thread that the suspending function is running on. It is not recommended to use this dispatcher for android development.

// Recommended best practice is always starting a coroutine from main thread and later switching it to a background thread.

// launch -> is coroutine builder which is launches a new coroutine without blocking current thread =? that returns an instance of Job, which can be use as a reference to the coroutine. We can use that returned job instance to keep track of the coroutine and to cancel the coroutine. We use launch builder for coroutines that does not have any result as return value. This builder returns a Job instance but does not return any computational result. We cannot use this coroutine to calculate something and get the final answer as the returned value.
// async -> If we want get such result as a returned value we should use async coroutine builder. Not only that, main specialty of async builder is that it allows us to launch coroutines in paralle. Async builder also launches a new coroutine without blocking the current thread. Returns an instance of Deferred<T>. We need to invoke await() to get the value.
// produce -> is for coroutines which produces a stream of elements. Returns an instance of ReceiveChannel
// runblocking -> In android development we use runblocking builder, mostly for testing. Not like other coroutines, the coroutine we create using this builder will block nthe thread until its execution is over. And it returns which we can directly use.

// suspend function => if we are going to use a suspending function, we have to mark our calling function with suspend modifier. And also if we are going to invoke another suspending function created by as, we have to also mark that calling function with suspend modifier. With suspending modifier, we are actually limiting te use of the function only for coroutines. A suspending function can be called from a coroutine block or from another suspending function only. we cannot invoke a suspending function from a normal function or from other places of the code. Actually with suspending modifier we label a function with a heavy long running task. And remember a coroutine can invoke both suspending and regular function, but a suspending function can be invoked by a coroutine.

// withContext(Dispatchers.Main){} -> Using withContext function we can switch a coroutine from one thread to another thread. It is suspend function and this have to call from suspend function
// withTimeout()
// withTimeoutOrNull()
// join()
// delay()
// await

class CoroutineIntroActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_intro)

        btnClick.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
        unstructuredConcurrency.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                tvMessage.text = UserDataManagerUnStructuredConcurrency().getTotalUserCount().toString()
            }
        }
        structuredConcurrency.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                tvMessage.text = UserDataManagerStructuredConcurrency().getTotalUserCount().toString()
            }
        }
    }

    private suspend  fun downloadUserData() {
        withContext(Dispatchers.Main) {
            for (i in 1..50000) {
//            Log.i("mytag","Downloading user $i in ${Thread.currentThread().name}")

                tvMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
                delay(10)
            }
        }
    }
}
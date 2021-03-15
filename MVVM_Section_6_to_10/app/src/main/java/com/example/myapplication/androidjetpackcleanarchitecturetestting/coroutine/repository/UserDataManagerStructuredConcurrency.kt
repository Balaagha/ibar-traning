package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository

import kotlinx.coroutines.*

class UserDataManagerStructuredConcurrency {
    suspend fun getTotalUserCount(): Int{
        var count = 0
        lateinit var deferred: Deferred<Int>

        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50

            }
            deferred = async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}
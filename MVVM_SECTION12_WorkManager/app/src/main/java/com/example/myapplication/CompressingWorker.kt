package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CompressingWorker(context: Context, params: WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        return try {
            for (i in 0..300000){
                Log.i("myTag","Compressing $i")
            }
            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }
}
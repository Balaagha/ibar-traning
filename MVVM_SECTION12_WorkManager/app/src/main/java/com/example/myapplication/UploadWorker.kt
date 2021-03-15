package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters): Worker(context,params) {
    companion object{
        const val KEY_WORKER = "key_worker"
    }
    // If any error happen this function(doWork) return failure() as result
    override fun doWork(): Result {
        return try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE,0)
            for (i in 0 until count){
                Log.i("myTag","Uploading $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentData = time.format(Date())
            val  outPutData = Data.Builder()
                .putString(KEY_WORKER,currentData)
                .build()

            Result.success(outPutData)
        }catch (e:Exception){
            Result.failure()
        }
    }
}
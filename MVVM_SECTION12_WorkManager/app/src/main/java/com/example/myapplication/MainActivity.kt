package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.btnStart.setOnClickListener {
//            setOneTimeWorkerRequest()
            setPeriodicWorkRequest()
        }
    }

    private fun setOneTimeWorkerRequest(){
        val workManager = WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
                .putInt(KEY_COUNT_VALUE,1000000)
                .build()

        val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .setConstraints(constraints)
                .setInputData(data)
                .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()
        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java).build()
        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java).build()

        val parallelWorks: MutableList<OneTimeWorkRequest> = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingRequest)
        parallelWorks.add(filteringRequest)
        workManager.beginWith(parallelWorks)
                .then(compressingRequest)
                .then(uploadRequest)
                .enqueue()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
                .observe(this, Observer {
                    txtCount.text = it.state.name
                    if(it.state.isFinished){
                        val data = it.outputData
                        val message = data.getString(UploadWorker.KEY_WORKER)
                        Toast.makeText(this@MainActivity,message,Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun setPeriodicWorkRequest(){
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java,16,TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}
package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelID = "com.example.myapplication.chanel1"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLY = "key_reply"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "this is a demo")

        binding.btnDisplayNotification.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification() {
        val notificationId = 45
        // Handle tap result
        val tapResultIntent = Intent(this@MainActivity, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
                this@MainActivity, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        // Reply Action
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert Your name here")
            build()
        }
        val actionReply: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "REPLY", pendingIntent).addRemoteInput(remoteInput).build()

        // Action button one
        val IntentActionOne = Intent(this@MainActivity, DetailActivity::class.java)
        val pendingActionOne: PendingIntent = PendingIntent.getActivity(
                this@MainActivity, 0, IntentActionOne, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val actionOne: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingActionOne).build()

        // Action button two
        val IntentActionTwo = Intent(this@MainActivity, SettingsActivity::class.java)
        val pendingActionTwo: PendingIntent = PendingIntent.getActivity(
                this@MainActivity, 0, IntentActionTwo, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val actionTwo: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Settings", pendingActionTwo).build()


        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification text")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(actionOne)
                .addAction(actionTwo)
                .addAction(actionReply     )
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
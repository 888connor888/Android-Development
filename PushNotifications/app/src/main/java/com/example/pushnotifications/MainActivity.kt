package com.example.pushnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ // for oreo and above
            val channel = NotificationChannel("First","Default",NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nm.createNotificationChannel(channel)
        }
        button1.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this,"First")
                .setContentTitle("Simple Title")
                .setContentText("This is a sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,simpleNotification)
        }
        // Clickable Notifications
        button2.setOnClickListener {
            val intent  = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")
            val pi = PendingIntent.getActivity(this@MainActivity,123,intent,PendingIntent.FLAG_UPDATE_CURRENT) // request code matters when opening the app
            val clickableNotification = NotificationCompat.Builder(this,"First")
                .setContentTitle("Simple Title")
                .setContentIntent(pi)
                .setAutoCancel(true) // dismiss the notification after click
                .setContentText("This is a sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }
        button3.setOnClickListener {
            val intent  = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")
            val pi = PendingIntent.getActivity(this@MainActivity,123,intent,PendingIntent.FLAG_UPDATE_CURRENT) // request code matters when opening the app
            val clickableNotification = NotificationCompat.Builder(this,"First")
                    .setContentTitle("Simple Title")
                    .addAction(R.drawable.ic_launcher_foreground,"Click me!",pi)
                    .setAutoCancel(true) // dismiss the notification after click
                    .setContentText("This is a sample description of the notification")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            nm.notify(2,clickableNotification)
        }
        // Heads up Notification
        headsUpButton.setOnClickListener {
            val builder = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Notification.Builder(this,"First")
                        .setPriority(Notification.PRIORITY_MAX)
            } else {
                Notification.Builder(this)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
            }
            val simpleNotification = builder
                    .setContentTitle("Heads Up Title!")
                    .setAutoCancel(true) // dismiss the notification after click
                    .setContentText("This is a sample description of the notification")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build()
            nm.notify(4,simpleNotification)
        }
    }
}
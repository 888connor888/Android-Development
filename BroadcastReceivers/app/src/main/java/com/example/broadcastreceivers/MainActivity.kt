package com.example.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val psr = PowerStateReceiver()
        val iFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        registerReceiver(psr,iFilter)
    }
    // Dynamic Broadcast Receiver (Implementation)
    inner class PowerStateReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action == Intent.ACTION_POWER_CONNECTED){
                Toast.makeText(context,"Charger Connected",Toast.LENGTH_SHORT).show()
            }
            if(intent?.action == Intent.ACTION_POWER_DISCONNECTED){
                Toast.makeText(context,"Charger Disconnected",Toast.LENGTH_SHORT).show()
            }
            if(intent?.action == null) return
        }

    }
}
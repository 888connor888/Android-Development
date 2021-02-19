package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val id = intent.getStringExtra("ID")
    }
}
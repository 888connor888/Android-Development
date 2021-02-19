package com.example.jobscheduler

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleJob.setOnClickListener {
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo = JobInfo.Builder(123, ComponentName(this,DemoJob::class.java))
                .setPeriodic(1200000)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .build()
            jobScheduler.schedule(jobInfo)
        }
    }
}
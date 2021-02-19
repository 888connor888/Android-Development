package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class DemoJob: JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Toast.makeText(this,"Hello I'm a scheduled job!",Toast.LENGTH_SHORT).show()
        // return true for async job
//        jobFinished(params,false)
        return false // true means that work is still going on so don't finish this job yet
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}
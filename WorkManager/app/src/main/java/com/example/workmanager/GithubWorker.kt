package com.example.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager.retrofit.Client
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GithubWorker(val context: Context, val params:WorkerParameters):CoroutineWorker(context,params) {
    override suspend fun doWork(): Result {
        val response = withContext(IO){Client.api.getUsers()}
        return if(response.isSuccessful){
            Result.success()
        } else {
            Result.retry()
        }
    }
}
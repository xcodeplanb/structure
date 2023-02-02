package com.example.structure.fcm

import android.content.Context
import android.util.Log

import androidx.work.Worker
import androidx.work.WorkerParameters

class IJobWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Log.d(TAG, "Performing long running task in scheduled job")
        return Result.success()
    }

    companion object {
        private val TAG = "MyWorker"
    }
}

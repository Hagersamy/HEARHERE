package com.example.hearhere.repository.remote

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // تهيئة RetrofitInstance
        RetrofitInstance.initialize(this)
    }
}
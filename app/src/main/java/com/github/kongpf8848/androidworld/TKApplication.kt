package com.github.kongpf8848.androidworld

import android.app.Application

class TKApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            e.printStackTrace()
        }
    }
}
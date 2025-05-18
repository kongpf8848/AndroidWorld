package com.github.kongpf8848.androidworld

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.github.kongpf8848.androidworld.utils.LanguageUtils
import io.github.kongpf8848.commonhelper.ToastHelper


class TKApplication : Application() {

    companion object {
        lateinit var application: TKApplication
        fun getApplication(): Application {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()

        application = this
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            e.printStackTrace()
        }
        ToastHelper.init(this)

        Looper.myLooper()?.setMessageLogging {
            //Log.d("MessageLogging", "message:$it");
        }

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityCreated() called with: activity = $activity"
                )
                LanguageUtils.applyLanguage(activity)
                LanguageUtils.applyLanguage(application)
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityStarted() called with: activity = $activity"
                )
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityResumed() called with: activity = $activity"
                )
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityPaused() called with: activity = $activity"
                )
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityStopped() called with: activity = $activity"
                )
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivitySaveInstanceState() called with: activity = $activity, outState = $outState"
                )
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(
                    activity.javaClass.simpleName,
                    "onActivityDestroyed() called with: activity = $activity"
                )
            }

        })
    }
}
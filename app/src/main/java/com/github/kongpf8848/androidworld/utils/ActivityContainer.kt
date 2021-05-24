package com.github.kongpf8848.androidworld.utils

import android.app.Activity
import com.github.kongpf8848.androidworld.activity.BaseActivity
import java.lang.ref.WeakReference


object ActivityContainer {

    private val acts: MutableList<WeakReference<BaseActivity>> = mutableListOf()

    fun add(activity: BaseActivity) {
        acts.add(WeakReference(activity))
    }

    fun remove(activity: BaseActivity) {
        acts.remove(WeakReference(activity))
    }

    fun getPenultimateActivity(): Activity? {
        var activity: WeakReference<BaseActivity>? = null
        try {
            if (acts.size > 1) {
                activity = acts[acts.size - 2]
            }
        } catch (ignored: Exception) {
        }
        return activity?.get()
    }

}
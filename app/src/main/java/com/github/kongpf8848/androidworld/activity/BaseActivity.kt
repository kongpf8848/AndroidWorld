package com.github.kongpf8848.androidworld.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.swipeback.SwipeBackActivity
import com.kongpf.commonhelper.ScreenHelper

abstract class BaseActivity : SwipeBackActivity() {

    val TAG: String = javaClass.simpleName

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateStart(savedInstanceState)
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(getLayoutId())
        onCreateEnd(savedInstanceState)
    }


    protected open fun onCreateStart(savedInstanceState: Bundle?) {}
    protected open fun onCreateEnd(savedInstanceState: Bundle?) {}

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent() called")
    }

    @Override
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d(TAG, "onPostCreate() called")
        if (enableStatusBar()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            var uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (statusBarDarkFont()) {
                    uiFlags = uiFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (navigationBarDarkFont()) {
                    uiFlags = uiFlags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }
            window.decorView.systemUiVisibility = uiFlags
            val statusBarHeight = ScreenHelper.getStatusbarHeight(this)
            var statusBarView = window.decorView.findViewById(R.id.status_bar_id) as View?
            if (statusBarView == null) {
                statusBarView = View(this)
                statusBarView.id = R.id.status_bar_id
                statusBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        statusBarColor()
                    )
                )
                val contentView = window.decorView.findViewById(android.R.id.content) as ViewGroup
                contentView.getChildAt(0).setPadding(0, statusBarHeight, 0, 0)
                contentView.addView(
                    statusBarView,
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
                )
            }
            window.navigationBarColor = ContextCompat.getColor(applicationContext, navigationBarColor())
        } else {
            customInitStatusBar()
        }
    }

    protected open fun enableStatusBar(): Boolean {
        return true
    }

    protected open fun customInitStatusBar() {}

    protected open fun statusBarColor(): Int {
        return R.color.white
    }

    protected open fun navigationBarColor(): Int {
        return R.color.white
    }

    protected open fun statusBarDarkFont(): Boolean {
        return true
    }

    protected open fun navigationBarDarkFont(): Boolean {
        return true
    }

}
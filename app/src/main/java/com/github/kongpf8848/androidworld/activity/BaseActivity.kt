package com.github.kongpf8848.androidworld.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.utils.ActivityContainer
import com.github.kongpf8848.androidworld.utils.LanguageUtils
import com.kongpf.commonhelper.ScreenHelper
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

abstract class BaseActivity : SwipeBackActivity(),SwipeBackLayout.SwipeListener {

    val TAG: String = javaClass.simpleName

    private val languageReceiver =object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (LanguageUtils.LANGUAGE_ACTION == action) {
                recreate()
            }
        }
    }

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateStart(savedInstanceState)
        Log.d(TAG, "onCreateStart called")
        super.onCreate(savedInstanceState)
        ActivityContainer.add(this)
        Log.d(TAG, "onCreate11 called")
        setContentView(getLayoutId())
        registerReceiver(languageReceiver, IntentFilter(LanguageUtils.LANGUAGE_ACTION))
        Log.d(TAG, "onCreate22 called")
        onCreateEnd(savedInstanceState)
        Log.d(TAG, "onCreateEnd called")
        swipeBackLayout.addSwipeListener(this)

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
        ActivityContainer.remove(this)
        unregisterReceiver(languageReceiver)
        Log.d(TAG, "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        Log.d(TAG, "attachBaseContext() called")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent() called")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow() called")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow() called")
    }

    override fun onEdgeTouch(edgeFlag: Int) {
        Log.d(TAG, "onEdgeTouch() called with: edgeFlag = $edgeFlag")
    }

    override fun onScrollOverThreshold() {
        Log.d(TAG, "onScrollOverThreshold() called")
    }

    override fun onScrollStateChange(state: Int, scrollPercent: Float) {
        Log.d(
            TAG,
            "onScrollStateChange() called with: state = $state, scrollPercent = $scrollPercent"
        )

    }

    @Override
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        /**
         * ??????????????????????????????????????????
         */
        window.statusBarColor = Color.TRANSPARENT
        var uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /**
             * ?????????????????????
             */
            if(statusBarDarkFont()) {
                uiFlags = uiFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**
             * ?????????????????????
             */
            if(navigationBarDarkFont()) {
                uiFlags = uiFlags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
        }
        window.decorView.systemUiVisibility = uiFlags

        /**
         * ?????????????????????
         */
        val statusBarHeight = ScreenHelper.getStatusbarHeight(this)

        /**
         * ???????????????View
         */
        var statusBarView=getStatusBarView()
        if(statusBarView==null) {
            statusBarView = View(this)
            statusBarView.id = R.id.status_bar_id
            val contentView = window.decorView.findViewById(android.R.id.content) as ViewGroup
            /**
             * ??????Padding
             */
            contentView.getChildAt(0).setPadding(0, statusBarHeight, 0, 0)
            /**
             * ?????????????????????
             */
            contentView.addView(statusBarView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight))
        }
        else{
            setStatusBarHeight(statusBarView)
        }
        statusBarView.setBackgroundColor(ContextCompat.getColor(applicationContext,statusBarColor()))
        window.navigationBarColor = ContextCompat.getColor(applicationContext,navigationBarColor())
    }

    /**
     * ?????????????????????????????????
     */
    fun setStatusBarDarkFont(darkFont:Boolean){
        var uiFlags =View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(darkFont) {
                uiFlags = uiFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            else{
                uiFlags =uiFlags or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
        window.decorView.systemUiVisibility = uiFlags
    }

    /**
     * ??????View????????????????????????
     */
    fun setStatusBarHeight(view:View?) {
        if(view==null){
            return
        }
        val fixHeight=ScreenHelper.getStatusbarHeight(this)
        var layoutParams = view.layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT || layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            val finalLayoutParams = layoutParams
            view.post(Runnable {
                finalLayoutParams.height = fixHeight
                view.layoutParams = finalLayoutParams
            })
        } else {
            layoutParams.height = fixHeight
            view.layoutParams = layoutParams
        }
    }

    /**
     * ???????????????????????????View?????????null?????????????????????View?????????????????????
     */
    protected open fun getStatusBarView(): View? {
        return null
    }

    /**
     * ???????????????
     */
    protected open fun statusBarColor(): Int {
        return R.color.white
    }

    /**
     * ???????????????
     */
    protected open fun navigationBarColor(): Int {
        return R.color.white
    }

    /**
     * ?????????????????????????????????
     */
    protected open fun statusBarDarkFont(): Boolean {
        return true
    }

    /**
     * ?????????????????????????????????
     */
    protected open fun navigationBarDarkFont(): Boolean {
        return true
    }

}
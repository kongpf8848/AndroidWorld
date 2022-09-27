package com.github.kongpf8848.androidworld.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.startNewActivity
import com.github.kongpf8848.androidworld.utils.ScreenShotListenManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        setSwipeBackEnable(false)
        button1.setOnClickListener {
            startNewActivity(clazz = JSUserInfoActivity::class.java)
        }
        button2.setOnClickListener {
            startNewActivity(clazz = JSTabActivity::class.java)
        }
        button3.setOnClickListener {
            startNewActivity(clazz = ImageScaleTypeActivity::class.java)
        }
        button4.setOnClickListener {
            startNewActivity(clazz = GlideHolderActivity::class.java)
        }
        button5.setOnClickListener {
            startNewActivity(clazz = LanguageActivity::class.java)
        }
        button6.setOnClickListener {
            startNewActivity(clazz = GaussBlurActivity::class.java)
        }
        button7.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),0)
                return@setOnClickListener
            }
            ScreenShotListenManager.newInstance(applicationContext).apply {
                setListener {
                    Log.d(TAG, "onShot called:$it")
                }
            }.startListen()

        }

        button8.setOnClickListener {
            startNewActivity(clazz = OkHttpActivity::class.java)
        }
    }

    override fun onStop() {
        super.onStop()
        ScreenShotListenManager.newInstance(applicationContext).stopListen()
    }
}
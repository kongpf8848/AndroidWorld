package com.github.kongpf8848.androidworld.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityMainBinding
import com.github.kongpf8848.androidworld.extension.startNewActivity
import com.github.kongpf8848.androidworld.utils.ScreenShotListenManager


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        setSwipeBackEnable(false)
        binding.button1.setOnClickListener {
            startNewActivity(clazz = JSUserInfoActivity::class.java)
        }
        binding.button2.setOnClickListener {
            startNewActivity(clazz = JSTabActivity::class.java)
        }
        binding.button3.setOnClickListener {
            startNewActivity(clazz = ImageScaleTypeActivity::class.java)
        }
        binding.button4.setOnClickListener {
            startNewActivity(clazz = GlideHolderActivity::class.java)
        }
        binding.button5.setOnClickListener {
            startNewActivity(clazz = LanguageActivity::class.java)
        }
        binding.button6.setOnClickListener {
            startNewActivity(clazz = GaussBlurActivity::class.java)
        }
        binding.button7.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    0
                )
                return@setOnClickListener
            }
            ScreenShotListenManager.newInstance(applicationContext).apply {
                setListener {
                    Log.d(TAG, "onShot called:$it")
                }
            }.startListen()

        }

        binding.button8.setOnClickListener {
            startNewActivity(clazz = OkHttpActivity::class.java)
        }

        binding.button9.setOnClickListener {
            startNewActivity(clazz = KeyboardActivity::class.java)
        }
        binding.button10.setOnClickListener {
            startNewActivity(clazz = ParcelActivity::class.java)
        }
        binding.button11.setOnClickListener {
            startNewActivity(clazz = ContentProviderActivity::class.java)
        }
        binding.button12.setOnClickListener {
            startNewActivity(clazz = WorkManagerActivity::class.java)
        }
    }

    override fun onStop() {
        super.onStop()
        ScreenShotListenManager.newInstance(applicationContext).stopListen()
    }
}
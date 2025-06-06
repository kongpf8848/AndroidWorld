package com.github.kongpf8848.androidworld.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.activity.task.TaskActivity
import com.github.kongpf8848.androidworld.databinding.ActivityMainBinding
import com.github.kongpf8848.androidworld.extension.startNewActivity
import com.github.kongpf8848.androidworld.utils.ScreenShotListenManager
import com.github.kongpf8848.androidworld.utils.UnPeekLiveData


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
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    0
                )
                return@setOnClickListener
            }
            startNewActivity(clazz = CameraActivity::class.java)
        }

        binding.button8.setOnClickListener {
            startNewActivity(clazz = OkHttpActivity::class.java)
        }

        binding.button9.setOnClickListener {
            startNewActivity(clazz = KeyboardActivity::class.java)
        }
        binding.button10.setOnClickListener {
            startNewActivity(clazz = TaskActivity::class.java)
        }
        binding.button11.setOnClickListener {
            startNewActivity(clazz = ContentProviderActivity::class.java)
        }
        binding.button12.setOnClickListener {
            startNewActivity(clazz = WorkManagerActivity::class.java)
        }
        binding.button13.setOnClickListener {
            startNewActivity(clazz =GalleryActivity::class.java)
        }
        binding.button14.setOnClickListener {
            startNewActivity(clazz = MyDataBindingActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
        ScreenShotListenManager.newInstance(applicationContext).stopListen()
    }
}
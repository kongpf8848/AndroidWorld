package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.util.ArrayMap
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.startNewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        setSwipeBackEnable(false)
        button1.setOnClickListener {
            startNewActivity(clazz= JSUserInfoActivity::class.java)
        }
        button2.setOnClickListener {
            startNewActivity(clazz=JSTabActivity::class.java)
        }
        button3.setOnClickListener {
            startNewActivity(clazz=ImageScaleTypeActivity::class.java)
        }
        button4.setOnClickListener {
            startNewActivity(clazz=GlideHolderActivity::class.java)
        }
    }
}
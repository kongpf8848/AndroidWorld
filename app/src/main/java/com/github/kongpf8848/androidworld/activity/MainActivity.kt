package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.startNewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun canUseSwipeBackLayout(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        button1.setOnClickListener {
            startNewActivity(clazz= JSUserInfoActivity::class.java)
        }
        button2.setOnClickListener {
            startNewActivity(clazz=ImageScaleTypeActivity::class.java)
        }
    }
}
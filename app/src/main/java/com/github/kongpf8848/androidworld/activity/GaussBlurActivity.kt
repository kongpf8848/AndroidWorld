package com.github.kongpf8848.androidworld.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Looper
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_gauss_blur.*

class GaussBlurActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_gauss_blur
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        Looper.myQueue().addIdleHandler {
            val oldBitmap=BitmapFactory.decodeResource(resources,R.mipmap.ic_girl)
            val newBitmap= ImageUtils.rsBlur(this,oldBitmap,15)
            imageView2.setImageBitmap(newBitmap)

            false
        }
    }
}
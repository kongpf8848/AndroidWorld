package com.github.kongpf8848.androidworld.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Looper
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityGaussBlurBinding
import com.github.kongpf8848.androidworld.utils.ImageUtils


class GaussBlurActivity : BaseActivity<ActivityGaussBlurBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_gauss_blur
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        setContentView(binding.root)
        Looper.myQueue().addIdleHandler {
            val oldBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_girl)
            val newBitmap = ImageUtils.rsBlur(this, oldBitmap, 15)
            binding.imageView2.setImageBitmap(newBitmap)

            false
        }
    }
}
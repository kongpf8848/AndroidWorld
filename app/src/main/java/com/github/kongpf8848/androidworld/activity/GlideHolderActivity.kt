package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.os.Looper
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityGlideHolderBinding
import com.github.kongpf8848.androidworld.extension.loadWithAnimation


class GlideHolderActivity : BaseActivity<ActivityGlideHolderBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_glide_holder
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        Looper.myQueue().addIdleHandler {
            loadImage()
            false
        }

    }

    private fun loadImage() {
        binding.imageView.loadWithAnimation(
            this,
            "http://image.huajiao.com/943063fe44aa1bcc4ece46ccbf358af8.jpg"
        )
    }
}
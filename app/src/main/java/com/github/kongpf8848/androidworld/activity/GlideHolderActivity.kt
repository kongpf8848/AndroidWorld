package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.os.Looper
import com.bumptech.glide.Glide
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.loadWithAnimation
import kotlinx.android.synthetic.main.activity_glide_holder.*
import kotlinx.android.synthetic.main.activity_glide_holder.toolbar

class GlideHolderActivity : BaseActivity() {

    override fun getLayoutId(): Int {
      return R.layout.activity_glide_holder
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        Looper.myQueue().addIdleHandler {
            loadImage()
            false
        }

    }

    private fun loadImage(){
        imageView.loadWithAnimation(this,"http://image.huajiao.com/943063fe44aa1bcc4ece46ccbf358af8.jpg")
    }
}
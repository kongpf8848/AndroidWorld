package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.os.Looper
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityImageScaleTypeBinding
import com.github.kongpf8848.androidworld.extension.*


class ImageScaleTypeActivity : BaseActivity<ActivityImageScaleTypeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_image_scale_type
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        Looper.myQueue().addIdleHandler {

//          iv_image_fitCenter.scaleType=ImageView.ScaleType.FIT_CENTER
//          iv_image_center.scaleType=ImageView.ScaleType.CENTER
//          iv_image_centerCrop.scaleType=ImageView.ScaleType.CENTER_CROP
//          iv_image_centerInside.scaleType=ImageView.ScaleType.CENTER_INSIDE
//          iv_image_fitStart.scaleType=ImageView.ScaleType.FIT_START
//          iv_image_fitEnd.scaleType=ImageView.ScaleType.FIT_END
//          iv_image_fitXY.scaleType=ImageView.ScaleType.FIT_XY

            binding.ivImageFitCenter.fitCenter()
            binding.ivImageCenter.center()
            binding.ivImageCenterCrop.centerCrop()
            binding.ivImageCenterInside.centerInside()
            binding.ivImageFitStart.fitStart()
            binding.ivImageFitEnd.fitEnd()
            binding.ivImageFitXY.fitXY()

            false
        }
    }

}
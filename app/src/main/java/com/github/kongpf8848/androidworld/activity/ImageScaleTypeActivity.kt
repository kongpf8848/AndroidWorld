package com.github.kongpf8848.androidworld.activity
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.*
import kotlinx.android.synthetic.main.activity_image_scale_type.*

class ImageScaleTypeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_image_scale_type
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        Looper.myQueue().addIdleHandler {

//          iv_image_fitCenter.scaleType=ImageView.ScaleType.FIT_CENTER
//          iv_image_center.scaleType=ImageView.ScaleType.CENTER
//          iv_image_centerCrop.scaleType=ImageView.ScaleType.CENTER_CROP
//          iv_image_centerInside.scaleType=ImageView.ScaleType.CENTER_INSIDE
//          iv_image_fitStart.scaleType=ImageView.ScaleType.FIT_START
//          iv_image_fitEnd.scaleType=ImageView.ScaleType.FIT_END
//          iv_image_fitXY.scaleType=ImageView.ScaleType.FIT_XY

            iv_image_fitCenter.fitCenter()
            iv_image_center.center()
            iv_image_centerCrop.centerCrop()
            iv_image_centerInside.centerInside()
            iv_image_fitStart.fitStart()
            iv_image_fitEnd.fitEnd()
            iv_image_fitXY.fitXY()

            false
        }
    }

}
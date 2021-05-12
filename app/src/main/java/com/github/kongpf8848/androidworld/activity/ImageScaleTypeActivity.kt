package com.github.kongpf8848.androidworld.activity
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.extension.*
import kotlinx.android.synthetic.main.activity_image_scale_type.*

/**
 * FIT_CENTER
   Scale the image using CENTER.
   CENTER: Compute a scale that will maintain the original src aspect ratio,
   but will also ensure that src fits entirely inside dst.
   At least one axis (X or Y) will fit exactly. The result is centered inside dst.
 1.保持图片的宽高比
 2.将图片放置到ImagetView的中心，然后进行向外按比例放大或者缩小，直到一个方向已经适应
 ============================================================================================
 * CENTER
 * Center the image in the view, but perform no scaling.
 * 当图片小于ImageView的宽高:直接居中显示该图片。
 * 当图片大于ImageView的宽高：以图片的中心点和ImageView的中心点为基准，按照图片的原大小居 中显示，不缩放，用ImageView的大小截取图片的居中部分
 ============================================================================================
 * CENTER_CROP
 * Scale the image uniformly (maintain the image's aspect ratio) so that both dimensions (width and height)
 * of the image will be equal to or larger than the corresponding dimension of the view (minus padding).
 * 先把当前的图片放置到ImageView的中间，执行缩放，将图片的宽度和高度按照相同比例缩放到宽度大于或者等于ImageView的宽度，
 * 同时高度大于或者等于ImageView的高度
 ============================================================================================
 * CENTER_INSIDE
   Scale the image uniformly (maintain the image's aspect ratio) so that both dimensions (width and height)
   of the image will be equal to or less than the corresponding dimension of the view (minus padding).
   1. 先把当前的图片放置到ImageView的中间;
   2. 如果图片小于ImageView的大小，不执行缩放，原样显示；
   3. 否则开始缩放，将图片的宽度和高度按照相同比例缩放到宽度小于或者等于ImageView的宽度，同时高度小于或者等于ImageView的高度（此处的高度和宽度指的是内容的大小width/height-padding）
 ============================================================================================
* FIT_START
  Scale the image using START
  START: Compute a scale that will maintain the original src aspect ratio, but will also
  ensure that src fits entirely inside dst. At least one axis (X or Y) will fit exactly.
  START aligns the result to the left and top edges of dst.
  1.保持图片的宽高比
  2.将图片放置到ImagetView的左上角，然后进行向外按比例放大或者缩小，直到一个方向已经适应
 ============================================================================================
 * FIT_END
 * Scale the image using END
   END: Compute a scale that will maintain the original src aspect ratio, but will also
   ensure that src fits entirely inside dst. At least one axis (X or Y) will fit exactly.
   END aligns the result to the right and bottom edges of dst.
   1.保持图片的宽高比
   2.将图片放置到ImagetView的右下角，然后进行向外按比例放大或者缩小，直到一个方向已经适应
  ============================================================================================
 * FIT_XY
 * Scale the image using FILL
   FILL: Scale in X and Y independently, so that src matches dst exactly.
   This may change the aspect ratio of the src.
   1.不保持图片的宽高比
   2.然后进行向外按比例放大或者缩小，直到每一个方向都适应
  ============================================================================================
 * MATRIX
   Scale using the image matrix when drawing.
   不改变原图的大小，从lmageView的左上角开始绘制原图，原图超过lmageView的部分做裁剪处理
 */

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
            iv_image_fitCenter.scaleType=ImageView.ScaleType.FIT_CENTER
            iv_image_center.scaleType=ImageView.ScaleType.CENTER
            iv_image_centerCrop.scaleType=ImageView.ScaleType.CENTER_CROP
            iv_image_centerInside.scaleType=ImageView.ScaleType.CENTER_INSIDE
            iv_image_fitStart.scaleType=ImageView.ScaleType.FIT_START
            iv_image_fitEnd.scaleType=ImageView.ScaleType.FIT_END
            iv_image_fitXY.scaleType=ImageView.ScaleType.FIT_XY
//            iv_image_fitCenter.fitCenter()
//            iv_image_center.center()
//            iv_image_centerCrop.centerCrop()
//            iv_image_centerInside.centerInside()
//            iv_image_fitStart.fitStart()
//            iv_image_fitEnd.fitEnd()
//            iv_image_fitXY.fitXY()
            false
        }
    }

}
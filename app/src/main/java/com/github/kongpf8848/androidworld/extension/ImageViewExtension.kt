package com.github.kongpf8848.androidworld.extension

import android.content.Context
import android.graphics.Matrix
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.kongpf8848.androidworld.R
import kotlin.math.roundToInt

fun ImageView.center() {
    if (drawable == null) {
        return
    }

    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val matrix = Matrix()
    matrix.setTranslate((width - dwidth) * 0.5f, (height - dheight) * 0.5f)

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix
}

fun ImageView.centerCrop() {

    if (drawable == null) {
        return
    }

    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val scale: Float
    val dx: Float
    val dy: Float

    if (dwidth * height > width * dheight) {
        scale = height.toFloat() / dheight.toFloat()
        dx = (width - dwidth * scale) * 0.5f
        dy = 0f
    } else {
        scale = width.toFloat() / dwidth.toFloat()
        dx = 0f
        dy = (height - dheight * scale) * 0.5f
    }

    /**
     * 设置Matrix
     */
    val matrix = Matrix()
    matrix.setScale(scale, scale)
    matrix.postTranslate(dx + 0.5f, dy + 0.5f)

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix
}

fun ImageView.centerInside() {
    if (drawable == null) {
        return
    }
    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    if (dwidth <= width && dheight <= height) {
        val matrix = Matrix()
        matrix.setTranslate((width - dwidth) * 0.5f, (height - dwidth) * 0.5f)
        scaleType = ImageView.ScaleType.MATRIX
        imageMatrix = matrix
    } else {
        fitCenter()
    }

}

fun ImageView.fitStart() {
    if (drawable == null) {
        return
    }
    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val widthPercentage = width.toFloat() / dwidth.toFloat()
    val heightPercentage = height.toFloat() / dheight.toFloat()
    val minPercentage = Math.min(widthPercentage, heightPercentage)

    val matrix = Matrix()
    matrix.setScale(minPercentage, minPercentage)

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix
}

fun ImageView.fitCenter() {
    if (drawable == null) {
        return
    }
    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val widthPercentage = width.toFloat() / dwidth.toFloat()
    val heightPercentage = height.toFloat() / dheight.toFloat()
    val minPercentage = Math.min(widthPercentage, heightPercentage)

    val targetWidth = (minPercentage * dwidth).roundToInt()
    val targetHeight = (minPercentage * dheight).roundToInt()

    val matrix = Matrix()
    matrix.setScale(minPercentage, minPercentage)
    matrix.postTranslate((width - targetWidth) * 0.5f, (height - targetHeight) * 0.5f)

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix

}

fun ImageView.fitEnd() {
    if (drawable == null) {
        return
    }
    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val widthPercentage = width.toFloat() / dwidth.toFloat()
    val heightPercentage = height.toFloat() / dheight.toFloat()
    val minPercentage = Math.min(widthPercentage, heightPercentage)

    val matrix = Matrix()
    val targetWidth = (minPercentage * dwidth).roundToInt()
    val targetHeight = (minPercentage * dheight).roundToInt()

    matrix.setScale(minPercentage, minPercentage)
    matrix.postTranslate((width - targetWidth).toFloat(), (height - targetHeight).toFloat())

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix

}

fun ImageView.fitXY() {
    if (drawable == null) {
        return
    }
    val dwidth = drawable.intrinsicWidth
    val dheight = drawable.intrinsicHeight

    val widthPercentage = width.toFloat() / dwidth.toFloat()
    val heightPercentage = height.toFloat() / dheight.toFloat()
    val matrix = Matrix()
    matrix.setScale(widthPercentage, heightPercentage)

    scaleType = ImageView.ScaleType.MATRIX
    imageMatrix = matrix
}

/**
 * 加载图片，带loading动画效果的占位符
 */
fun ImageView.loadWithAnimation(
    context: Context,
    url: String,
    listener: RequestListener<Drawable>? = null
) {

    val placeHolderDrawable = ContextCompat.getDrawable(context, R.drawable.place_holder_animation)
    var animationDrawable: Drawable? = null
    if (placeHolderDrawable is LayerDrawable) {
        animationDrawable = placeHolderDrawable.findDrawableByLayerId(R.id.layer_animation)
        if (animationDrawable is AnimationDrawable) {
            animationDrawable.start()
        }
    }
    val callback = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            listener?.onLoadFailed(e, model, target, isFirstResource)
            return false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            listener?.onResourceReady(resource, model, target, dataSource, isFirstResource)
            if (animationDrawable is AnimationDrawable) {
                if (animationDrawable.isRunning) {
                    animationDrawable.stop()
                }
            }
            return false
        }
    }
    Glide.with(context).load(url).placeholder(placeHolderDrawable).listener(callback).into(this)
}
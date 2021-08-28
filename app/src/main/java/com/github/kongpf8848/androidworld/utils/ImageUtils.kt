package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


object ImageUtils {

    fun rsBlur(context: Context, source: Bitmap, radius: Int): Bitmap {

        val inputBmp = source
        val renderScript = RenderScript.create(context)
        val input = Allocation.createFromBitmap(renderScript, inputBmp)
        val output = Allocation.createTyped(renderScript, input.type)
        val scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        scriptIntrinsicBlur.setInput(input)
        scriptIntrinsicBlur.setRadius(radius.toFloat())
        scriptIntrinsicBlur.forEach(output)
        output.copyTo(inputBmp)
        renderScript.destroy()
        return inputBmp
    }
}
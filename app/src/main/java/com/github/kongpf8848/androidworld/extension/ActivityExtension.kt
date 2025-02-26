package com.github.kongpf8848.androidworld.extension

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.github.kongpf8848.commonhelper.ScreenHelper

fun AppCompatActivity.dp2px(dp:Float):Int{
    return ScreenHelper.dp2px(this,dp)
}

fun AppCompatActivity.startNewActivity(clazz: Class<*>?, finishThis: Boolean = false) {
    val intent = Intent(this, clazz)
    startActivity(intent)
    if (finishThis) {
        finish()
    }
}



package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

object ApkUtils {

    /**
     * 安装Apk
     */
    fun installApk(context: Context, uri:Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

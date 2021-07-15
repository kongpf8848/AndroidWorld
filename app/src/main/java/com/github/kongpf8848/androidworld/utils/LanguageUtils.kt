package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.text.TextUtils
import android.util.Log
import java.util.*

object LanguageUtils {

    const val LANGUAGE_ENGLISH = "en_US"
    const val LANGUAGE_CHINESE = "zh_CN"
    const val LANGUAGE_ENGLISH_DESCRIPTION = "English"
    const val LANGUAGE_CHINESE_DESCRIPTION = "简体中文"
    val LANGUAGE_LIST= listOf(LANGUAGE_CHINESE to LANGUAGE_CHINESE_DESCRIPTION,LANGUAGE_ENGLISH to LANGUAGE_ENGLISH_DESCRIPTION)

    /**
     * 广播Action
     */
    const val LANGUAGE_ACTION="language_action"

    /**
     * 默认Locale为简体中文
     */
    private val DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE

    /**
     * 语言是否跟随系统
     */
    fun isFollowSystem(context: Context)= SharedPreferencesUtils.getBoolean(
        context = context,
        key = SharedPreferencesUtils.SP_KEY_LANGUAGE_FOLLOW_SYSTEM,
        defValue = true
    )

    /**
     * 获取app内当前使用的语言，默认为简体中文
     */
    fun getCurrentLanguage(context: Context)= SharedPreferencesUtils.getString(
        context = context,
        key = SharedPreferencesUtils.SP_KEY_LANGUAGE,
        defValue = LANGUAGE_CHINESE
    )

    /**
     * 语言设置，切换语言
     */
    fun switchLanguage(context: Context, isFollowSystem:Boolean,language: String?) {
        SharedPreferencesUtils.putBoolean(
            context = context,
            key = SharedPreferencesUtils.SP_KEY_LANGUAGE_FOLLOW_SYSTEM,
            value =isFollowSystem
        )
        SharedPreferencesUtils.putString(
            context = context,
            key = SharedPreferencesUtils.SP_KEY_LANGUAGE,
            value=language
        )
        context.sendBroadcast(Intent(LANGUAGE_ACTION))
    }


    /**
     * 适配语言，在Activity的onCreate方法里调用
     */
    fun applyLanguage(context: Context) {
        val destLocale=if(isFollowSystem(context)){
            getLocale(Resources.getSystem().configuration)
        }
        else {
            string2Locale(getCurrentLanguage(context))
        }
        /**
         * 如果app不支持此语言，则使用默认语言(简体中文)
         */
        if(!appSupportsTheExactLocale(destLocale)){
            updateConfiguration(context, DEFAULT_LOCALE)
        }
        else {
            updateConfiguration(context, destLocale)
        }
    }

    private fun updateConfiguration(context: Context, newLocale: Locale) {
        val config = context.resources.configuration
        val oldLocale = getLocale(config)
        Log.d("JACK8", "oldLocale:$oldLocale,newLocale:$newLocale")
        if (oldLocale == newLocale) {
            Log.d("JACK8", "+++++++++++++++++++++oldLocale==newLocale,return")
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            config.setLocale(newLocale)
            config.setLocales(localeList)
        } else {
            Locale.setDefault(newLocale)
            config.setLocale(newLocale)
        }
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    /**
     * 从Configuration里获取Locale
     */
    private fun getLocale(config: Configuration): Locale {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return config.locales.get(0)
        } else {
            return config.locale
        }
    }

    /**
     * 将字符串转换为Locale
     */
    private fun string2Locale(language: String?): Locale {
        if(TextUtils.isEmpty(language)){
            return DEFAULT_LOCALE
        }
        val array = language!!.split("_").toTypedArray()
        return if (array.size >= 2) {
            Locale(array[0], array[1])
        } else {
            Locale(array[0])
        }
    }

    /**
     * app是否支持指定的Locale
     */
    private fun appSupportsTheExactLocale(locale: Locale?): Boolean {
        return if (locale == null) {
            false
        } else {
            val language = StringBuilder().append(locale.language)
            if (!TextUtils.isEmpty(locale.country)) {
                language.append("_").append(locale.country)
            }
            LANGUAGE_LIST.map { it.first }.contains(language.toString())
        }
    }

}
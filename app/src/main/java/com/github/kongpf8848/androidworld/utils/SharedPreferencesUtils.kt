package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {

    const val SP_NAME = "SP_NORMAL"
    const val SP_KEY_LANGUAGE = "language"
    const val SP_KEY_LANGUAGE_FOLLOW_SYSTEM = "language_follow_system"


    private fun getSharedPreferences(context: Context, spName: String): SharedPreferences {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    fun putInt(context: Context, key: String, value: Int, spName: String = SP_NAME): Boolean {
        return getSharedPreferences(context, spName).edit().putInt(key, value).commit()
    }

    fun putLong(context: Context, key: String, value: Long, spName: String = SP_NAME): Boolean {
        return getSharedPreferences(context, spName).edit().putLong(key, value).commit()
    }

    fun putFloat(context: Context, key: String, value: Float, spName: String = SP_NAME): Boolean {
        return getSharedPreferences(context, spName).edit().putFloat(key, value).commit()
    }

    fun putBoolean(
        context: Context,
        key: String,
        value: Boolean,
        spName: String = SP_NAME
    ): Boolean {
        return getSharedPreferences(context, spName).edit().putBoolean(key, value).commit()
    }

    fun putString(
        context: Context,
        key: String,
        value: String?,
        spName: String = SP_NAME
    ): Boolean {
        return getSharedPreferences(context, spName).edit().putString(key, value).commit()
    }

    fun getInt(context: Context, key: String, defValue: Int = 0, spName: String = SP_NAME): Int {
        return getSharedPreferences(context, spName).getInt(key, defValue)
    }

    fun getLong(
        context: Context,
        key: String,
        defValue: Long = 0L,
        spName: String = SP_NAME
    ): Long {
        return getSharedPreferences(context, spName).getLong(key, defValue)
    }

    fun getFloat(
        context: Context,
        key: String,
        defValue: Float = 0f,
        spName: String = SP_NAME
    ): Float {
        return getSharedPreferences(context, spName).getFloat(key, defValue)
    }

    fun getBoolean(
        context: Context,
        key: String,
        defValue: Boolean,
        spName: String = SP_NAME
    ): Boolean {
        return getSharedPreferences(context, spName).getBoolean(key, defValue)
    }

    fun getString(
        context: Context,
        key: String,
        defValue: String? = "",
        spName: String = SP_NAME
    ): String? {
        return getSharedPreferences(context, spName).getString(key, defValue)
    }

    fun clear(context: Context, spName: String = SP_NAME): Boolean {
        return getSharedPreferences(context, spName).edit().clear().commit()
    }



}
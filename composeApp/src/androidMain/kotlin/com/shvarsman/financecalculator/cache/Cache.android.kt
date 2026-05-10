package com.shvarsman.financecalculator.cache

import android.content.Context
import android.content.SharedPreferences

actual class Cache {
    companion object {
        lateinit var appContext: Context
    }

    private val prefs: SharedPreferences by lazy {
        appContext.getSharedPreferences("finance_cache", Context.MODE_PRIVATE)
    }

    actual suspend fun save(key: String, data: String) {
        prefs.edit().putString(key, data).apply()
    }

    actual suspend fun load(key: String): String? {
        return prefs.getString(key, null)
    }
}
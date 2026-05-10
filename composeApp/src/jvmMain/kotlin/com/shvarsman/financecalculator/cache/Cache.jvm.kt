package com.shvarsman.financecalculator.cache

import java.io.File

actual class Cache {
    private val cacheDir = File(System.getProperty("user.home"), ".finance_cache").apply { mkdirs() }

    actual suspend fun save(key: String, data: String) {
        File(cacheDir, key).writeText(data)
    }

    actual suspend fun load(key: String): String? {
        val file = File(cacheDir, key)
        return if (file.exists()) file.readText() else null
    }
}
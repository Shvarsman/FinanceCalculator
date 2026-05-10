package com.shvarsman.financecalculator.cache

import platform.Foundation.NSUserDefaults

actual class Cache {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual suspend fun save(key: String, data: String) {
        defaults.setObject(data, forKey = key)
    }

    actual suspend fun load(key: String): String? {
        return defaults.stringForKey(key)
    }
}
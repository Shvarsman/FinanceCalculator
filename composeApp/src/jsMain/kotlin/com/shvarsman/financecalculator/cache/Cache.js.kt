package com.shvarsman.financecalculator.cache

import kotlinx.browser.localStorage

actual class Cache {
    actual suspend fun save(key: String, data: String) {
        localStorage.setItem(key, data)
    }

    actual suspend fun load(key: String): String? {
        return localStorage.getItem(key)
    }
}
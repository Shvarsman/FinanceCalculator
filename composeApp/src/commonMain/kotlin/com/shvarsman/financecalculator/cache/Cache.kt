package com.shvarsman.financecalculator.cache

expect class Cache() {
    suspend fun save(key: String, data: String)
    suspend fun load(key: String): String?
}
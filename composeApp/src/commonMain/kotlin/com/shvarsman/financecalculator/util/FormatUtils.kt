package com.shvarsman.financecalculator.util

import kotlin.math.roundToInt

fun formatMoney(amount: Double): String {
    val rounded = (amount * 100).roundToInt()
    val intPart = rounded / 100
    val fracPart = rounded % 100
    return "$intPart.${fracPart.toString().padStart(2, '0')}"
}
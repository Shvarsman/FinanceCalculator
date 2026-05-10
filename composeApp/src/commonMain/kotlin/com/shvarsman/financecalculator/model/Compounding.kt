package com.shvarsman.financecalculator.model

enum class Compounding(val timesPerYear: Int) {
    MONTHLY(12),
    QUARTERLY(4),
    ANNUALLY(1)
}
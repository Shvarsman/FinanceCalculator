package com.shvarsman.financecalculator.model

import kotlinx.serialization.Serializable

@Serializable
data class InputData(
    val principal: Double = 100000.0,
    val annualRate: Double = 5.0,
    val years: Int = 5,
    val compounding: Compounding = Compounding.MONTHLY
)
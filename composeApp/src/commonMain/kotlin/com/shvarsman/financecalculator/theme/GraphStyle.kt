package com.shvarsman.financecalculator.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class GraphStyle(
    val lineColor: Color,
    val fillBrush: Brush?,
    val lineWidth: Float
)

object DefaultGraphStyle {
    val androidStyle = GraphStyle(
        lineColor = Color(0xFF6200EE),
        fillBrush = Brush.verticalGradient(listOf(Color(0xAA6200EE), Color.Transparent)),
        lineWidth = 4f
    )
    val iosStyle = GraphStyle(
        lineColor = Color(0xFF007AFF),
        fillBrush = null,
        lineWidth = 2f
    )
    val desktopStyle = GraphStyle(
        lineColor = Color(0xFF333333),
        fillBrush = null,
        lineWidth = 2f
    )
    val webStyle = GraphStyle(
        lineColor = Color(0xFF1A73E8),
        fillBrush = Brush.verticalGradient(listOf(Color(0x551A73E8), Color.Transparent)),
        lineWidth = 3f
    )
}
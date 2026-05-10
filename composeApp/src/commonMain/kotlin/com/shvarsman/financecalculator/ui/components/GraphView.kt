package com.shvarsman.financecalculator.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.shvarsman.financecalculator.theme.GraphStyle

@Composable
fun GraphView(
    balances: List<Double>,
    maxValue: Double,
    style: GraphStyle
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val animatedProgress by animateFloatAsState(targetValue = 1f, animationSpec = tween(1000))

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 3f)
                    offsetX = (offsetX + pan.x).coerceIn(
                        -size.width * (scale - 1),
                        0f
                    )
                    offsetY = (offsetY + pan.y).coerceIn(
                        -size.height * (scale - 1),
                        0f
                    )
                }
            }
    ) {
        val canvasWidth = size.width * scale
        val canvasHeight = size.height * scale
        if (balances.size < 2) return@Canvas
        val stepX = canvasWidth / (balances.size - 1)
        val maxY = if (maxValue > 0) maxValue else 1.0

        val points = balances.mapIndexed { index, value ->
            Offset(
                x = index * stepX + offsetX,
                y = canvasHeight - (value / maxY * canvasHeight).toFloat() + offsetY
            )
        }

        // Заливка
        style.fillBrush?.let { brush ->
            val path = Path().apply {
                moveTo(points.first().x, canvasHeight)
                points.forEach { lineTo(it.x, it.y) }
                lineTo(points.last().x, canvasHeight)
                close()
            }
            drawPath(path, brush)
        }

        // Линия с анимацией появления
        val visibleCount = (points.size * animatedProgress).toInt().coerceIn(0, points.size)
        if (visibleCount > 1) {
            val linePath = Path().apply {
                moveTo(points[0].x, points[0].y)
                for (i in 1 until visibleCount) {
                    lineTo(points[i].x, points[i].y)
                }
            }
            drawPath(linePath, style.lineColor, style.lineWidth)
        }
    }
}
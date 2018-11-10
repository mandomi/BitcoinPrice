package com.mandomi.bitcoinprice.ui.chart

data class ChartItem(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val points: List<PointItem>,
    val maxValue: Float? = 0f,
    val minValue: Float? = 0f
)

data class PointItem(val timeSpan: Long, val value: Double, val stringTime: String)
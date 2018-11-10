package com.mandomi.bitcoinprice.ui.chart

data class ChartItem(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val points: List<PointItem>
)

data class PointItem(val x: String, val y: Double)
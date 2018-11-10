package com.mandomi.bitcoinprice.domain.entity

data class Chart(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val points: List<Point>
)

data class Point(val x: String, val y: Double)
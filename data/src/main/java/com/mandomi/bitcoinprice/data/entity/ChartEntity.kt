package com.mandomi.bitcoinprice.data.entity

import com.mandomi.bitcoinprice.domain.entity.Point

data class ChartEntity(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val points: List<Point>
)
package com.mandomi.bitcoinprice.domain.factory

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point

object ChartFactory {

    const val CHART_NAME = "market-price"
    const val TIME_SPAN = "1week"

    private val points = listOf(
        Point(1, 1.0),
        Point(2, 2.0),
        Point(3, 3.0),
        Point(4, 4.0),
        Point(5, 5.0),
        Point(6, 6.0),
        Point(7, 7.0)
    )

    val chart = Chart("market price", "USD", "1 week", "test", points)
}
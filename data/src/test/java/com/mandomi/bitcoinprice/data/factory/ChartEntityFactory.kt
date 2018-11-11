package com.mandomi.bitcoinprice.data.factory

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Point

object ChartEntityFactory {

    const val CHART_NAME = "market-price"
    const val WRONG_CHART_NAME = "market-price11"
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

    val chart = ChartEntity("market price", "USD", "1 week", "test",
        points
    )
}
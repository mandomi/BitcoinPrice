package com.mandomi.bitcoinprice.domain.factory

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.domain.factory.DataFactory.Factory.randomDouble
import com.mandomi.bitcoinprice.domain.factory.DataFactory.Factory.randomLong

internal class ChartFactory {

    companion object Factory {

        const val CHART_NAME = "market-price"
        const val TIME_SPAN = "1week"
        private const val PRICE_UNIT = "USD"

        fun makeChart(pointCount: Int): Chart {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return Chart(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        private fun makePoint() = Point(randomLong(), randomDouble())

    }

}
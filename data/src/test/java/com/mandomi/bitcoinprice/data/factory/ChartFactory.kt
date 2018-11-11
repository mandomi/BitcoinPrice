package com.mandomi.bitcoinprice.factory

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomDouble
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomLong

class ChartFactory {

    companion object Factory {

        const val CHART_NAME = "market-price"
        const val WRONG_CHART_NAME = "market-price11"
        const val TIME_SPAN = "1week"
        const val PRICE_UNIT = "USD"

        fun makeChart(pointCount: Int): Chart {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return Chart(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        fun makeChartEntity(pointCount: Int): ChartEntity {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return ChartEntity(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        private fun makePoint() = Point(randomLong(), randomDouble())

    }

}
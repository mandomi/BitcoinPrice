package com.mandomi.bitcoinprice.factory

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomDouble
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomLong
import com.mandomi.bitcoinprice.ui.chart.ChartItem
import com.mandomi.bitcoinprice.ui.chart.PointItem

internal class ChartFactory {

    companion object Factory {

        private const val CHART_NAME = "market-price"
        private const val TIME_SPAN = "1week"
        private const val PRICE_UNIT = "USD"


        fun makeChart(pointCount: Int): Chart {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return Chart(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        private fun makePoint() = Point(randomLong(), randomDouble())

        fun makeChartItem(pointCount: Int): ChartItem {
            val points = mutableListOf<PointItem>()
            repeat(pointCount) {
                points.add(makePointItem())
            }
            return ChartItem(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        private fun makePointItem() = PointItem(randomLong(), randomDouble(), "time")

    }

}
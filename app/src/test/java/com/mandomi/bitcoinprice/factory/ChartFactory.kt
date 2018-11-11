package com.mandomi.bitcoinprice.factory

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomDouble
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomLong
import com.mandomi.bitcoinprice.factory.DataFactory.Factory.randomString
import com.mandomi.bitcoinprice.ui.chart.ChartItem
import com.mandomi.bitcoinprice.ui.chart.PointItem

class ChartFactory {

    companion object Factory {

        fun makeChart(pointCount: Int): Chart {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return Chart(randomString(), randomString(), randomString(), randomString(), points)
        }

        private fun makePoint() = Point(randomLong(), randomDouble())

        fun makeChartItem(pointCount: Int): ChartItem {
            val points = mutableListOf<PointItem>()
            repeat(pointCount) {
                points.add(makePointItem())
            }
            return ChartItem(randomString(), randomString(), randomString(), randomString(), points)
        }

        private fun makePointItem() = PointItem(randomLong(), randomDouble(), randomString())

    }

}
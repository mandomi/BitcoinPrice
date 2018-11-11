package com.mandomi.bitcoinprice.remote.factory

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.remote.dto.ChartDTO
import com.mandomi.bitcoinprice.remote.dto.PointDTO
import com.mandomi.bitcoinprice.remote.factory.DataFactory.Factory.randomDouble
import com.mandomi.bitcoinprice.remote.factory.DataFactory.Factory.randomLong

internal class ChartFactory {

    companion object Factory {

        private const val CHART_NAME = "market-price"
        private const val TIME_SPAN = "1week"
        private const val PRICE_UNIT = "USD"

        fun makeChartEntity(pointCount: Int): ChartEntity {
            val points = mutableListOf<Point>()
            repeat(pointCount) {
                points.add(makePoint())
            }
            return ChartEntity(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        fun makeChartDto(pointCount: Int): ChartDTO {
            val points = mutableListOf<PointDTO>()
            repeat(pointCount) {
                points.add(makePointDto())
            }
            return ChartDTO(CHART_NAME, PRICE_UNIT, TIME_SPAN, "test", points)
        }

        private fun makePoint() = Point(randomLong(), randomDouble())

        private fun makePointDto() = PointDTO(randomLong(), randomDouble())

    }

}
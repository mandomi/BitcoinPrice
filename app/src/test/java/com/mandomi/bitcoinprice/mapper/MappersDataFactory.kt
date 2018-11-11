package com.mandomi.bitcoinprice.mapper

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.ui.chart.ChartItem
import com.mandomi.bitcoinprice.ui.chart.PointItem

object MappersDataFactory {

    data class ChartTestData(val input: Chart, val expected: ChartItem)

    fun validDataProvider() = ChartTestData(
        Chart("market-price", "USD", "1year", "test", points),
        ChartItem(
            "market-price",
            "USD",
            "1year",
            "test",
            pointItems,
            15265.906666666668.toFloat(),
            6260.645833333335.toFloat()
        )
    )

    private val points = listOf(
        Point(1538870400, 6558.537499999999),
        Point(1538956800, 6618.567692307693),
        Point(1539388800, 6260.645833333335),
        Point(1515369600, 15265.906666666668),
        Point(1516406400, 12950.793333333333),
        Point(1518134400, 8535.516666666668),
        Point(1525824000, 9322.041666666666),
        Point(1531008000, 6753.559166666666),
        Point(1533513600, 6988.079166666666),
        Point(1535673600, 6981.9461538461555)
    )

    private val pointItems = listOf(
        PointItem(1538870400, 6558.537499999999, "2018/10/07 00:00"),
        PointItem(1538956800, 6618.567692307693, "2018/10/08 00:00"),
        PointItem(1539388800, 6260.645833333335, "2018/10/13 00:00"),
        PointItem(1515369600, 15265.906666666668, "2018/01/08 00:00"),
        PointItem(1516406400, 12950.793333333333, "2018/01/20 00:00"),
        PointItem(1518134400, 8535.516666666668, "2018/02/09 00:00"),
        PointItem(1525824000, 9322.041666666666, "2018/05/09 00:00"),
        PointItem(1531008000, 6753.559166666666, "2018/07/08 00:00"),
        PointItem(1533513600, 6988.079166666666, "2018/08/06 00:00"),
        PointItem(1535673600, 6981.9461538461555, "2018/08/31 00:00")
    )
}
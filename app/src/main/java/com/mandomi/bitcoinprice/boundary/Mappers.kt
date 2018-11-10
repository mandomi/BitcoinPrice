package com.mandomi.bitcoinprice.boundary

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.extension.toMinuteDayMonthYear
import com.mandomi.bitcoinprice.ui.chart.ChartItem
import com.mandomi.bitcoinprice.ui.chart.PointItem

fun Chart.toChartItem() : ChartItem {
    return ChartItem(
        name,
        unit,
        period,
        description,
        points.map { it.toPointItem() },
        points.map { it.value }.max()?.toFloat(),
        points.map { it.value }.min()?.toFloat()
    )
}

fun Point.toPointItem() = PointItem(timeSpan, value, timeSpan.toMinuteDayMonthYear())
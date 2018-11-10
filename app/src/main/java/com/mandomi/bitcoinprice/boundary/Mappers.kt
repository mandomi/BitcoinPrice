package com.mandomi.bitcoinprice.boundary

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.ui.chart.ChartItem
import com.mandomi.bitcoinprice.ui.chart.PointItem

fun Chart.toChartItem() = ChartItem(name, unit, period, description, points.map { it.toPointItem() })

fun Point.toPointItem() = PointItem(x, y)
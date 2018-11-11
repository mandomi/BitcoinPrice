package com.mandomi.bitcoinprice.remote

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.remote.dto.ChartDTO
import com.mandomi.bitcoinprice.remote.dto.PointDTO

fun ChartDTO.toChartEntity() = ChartEntity(name, unit, period, description, points.map { it.toPoint() })

fun PointDTO.toPoint(): Point {
    return Point(timeSpan, value)
}
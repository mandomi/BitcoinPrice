package com.mandomi.bitcoinprice.remote

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Point
import com.mandomi.bitcoinprice.remote.dto.ChartDTO
import com.mandomi.bitcoinprice.remote.dto.PointDTO
import com.mandomi.bitcoinprice.remote.extension.toStringTime

fun ChartDTO.toChartEntity() = ChartEntity(name, unit, period, description, points.map { it.toPoint() })

private fun PointDTO.toPoint(): Point {
    return Point(x.toStringTime(), y)
}
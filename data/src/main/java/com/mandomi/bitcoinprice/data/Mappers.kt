package com.mandomi.bitcoinprice.data

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.domain.entity.Chart

fun ChartEntity.toChart() = Chart(name, unit, period, description, points)
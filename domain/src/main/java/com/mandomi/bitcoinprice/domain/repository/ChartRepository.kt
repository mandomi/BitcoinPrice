package com.mandomi.bitcoinprice.domain.repository

import com.mandomi.bitcoinprice.domain.entity.Chart
import io.reactivex.Single

interface ChartRepository {

    fun getChart(chartName: String, timeSpan: String, rollingAverage: String, start: String): Single<Chart>

}
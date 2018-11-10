package com.mandomi.bitcoinprice.data.datasource

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import io.reactivex.Single

interface ChartRemoteDataSource {

    fun getChart(chartName: String, timeSpan: String): Single<ChartEntity>

}
package com.mandomi.bitcoinprice.data.reporitory

import com.mandomi.bitcoinprice.data.datasource.ChartRemoteDataSource
import com.mandomi.bitcoinprice.data.toChart
import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
import io.reactivex.Single
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(private val chartRemoteDataSource: ChartRemoteDataSource) :
    ChartRepository {

    override fun getChart(chartName: String, timeSpan: String): Single<Chart> {
        return chartRemoteDataSource.getChart(chartName, timeSpan).map { it.toChart() }
    }

}
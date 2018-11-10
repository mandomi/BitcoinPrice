package com.mandomi.bitcoinprice.remote.datasource

import com.mandomi.bitcoinprice.data.datasource.ChartRemoteDataSource
import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.remote.RemoteConstants
import com.mandomi.bitcoinprice.remote.extension.toRxSingle
import com.mandomi.bitcoinprice.remote.retrofit.ChartService
import com.mandomi.bitcoinprice.remote.toChartEntity
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ChartRemoteDataSourceImpl @Inject constructor(okHttpClient: OkHttpClient) : ChartRemoteDataSource {

    private val service: ChartService = Retrofit.Builder().baseUrl(RemoteConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).build().create(ChartService::class.java)

    override fun getChart(
        chartName: String,
        timeSpan: String,
        rollingAverage: String,
        start: String
    ): Single<ChartEntity> {
        return service.getChart(chartName, timeSpan, rollingAverage, start).toRxSingle().map { it.toChartEntity() }
    }

}
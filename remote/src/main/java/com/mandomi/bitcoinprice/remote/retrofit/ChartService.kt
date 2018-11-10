package com.mandomi.bitcoinprice.remote.retrofit

import com.mandomi.bitcoinprice.remote.dto.ChartDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChartService {
    @GET("/charts/{chartName}")
    fun getChart(
        @Path("chartName") name: String,
        @Query("timeSpan") timeSpan: String = "1year",
        @Query("rollingAverage") rollingAverage: String = "24hours",
        @Query("start") start: String = ""
    ): Call<ChartDTO>
}
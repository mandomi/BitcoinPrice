package com.mandomi.bitcoinprice.remote.dto

import com.google.gson.annotations.SerializedName

data class ChartDTO(
    @SerializedName("name") val name: String,
    @SerializedName("unit") val unit: String,
    @SerializedName("period") val period: String,
    @SerializedName("description") val description: String,
    @SerializedName("values") val points: List<PointDTO>
)

data class PointDTO(@SerializedName("x") val x: Int, @SerializedName("y") val y: Double)
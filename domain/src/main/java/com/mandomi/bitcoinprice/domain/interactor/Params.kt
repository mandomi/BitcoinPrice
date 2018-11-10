package com.mandomi.bitcoinprice.domain.interactor

data class ChartUseCaseParams(
    val timeSpan: String = "1month",
    val rollingAverage: String = "8hours",
    val start: String = ""
)

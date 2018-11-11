package com.mandomi.bitcoinprice.domain.interactor

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.executer.PostExecutionThread
import com.mandomi.bitcoinprice.domain.executer.UseCaseExecutor
import com.mandomi.bitcoinprice.domain.interactor.base.SingleUseCase
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMarketPriceChartUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Chart, GetMarketPriceChartUseCase.Params>(useCaseExecutor, postExecutionThread) {

    override fun buildSingle(params: Params): Single<Chart> {
        return chartRepository.getChart("market-price", params.timeSpan)
    }

    data class Params(val timeSpan: String = "4weeks")

}

package com.mandomi.bitcoinprice.domain.usecase

import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.executer.PostExecutionThread
import com.mandomi.bitcoinprice.domain.executer.UseCaseExecutor
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
import com.mandomi.bitcoinprice.domain.factory.ChartFactory.Factory.CHART_NAME
import com.mandomi.bitcoinprice.domain.factory.ChartFactory.Factory.TIME_SPAN
import com.mandomi.bitcoinprice.domain.factory.ChartFactory.Factory.makeChart
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test


class MarketPriceUseCaseTest {

    private val repository: ChartRepository = mockk()
    private val useCaseExecutor: UseCaseExecutor = mockk(relaxed = true)
    private val postExecutionThread: PostExecutionThread = mockk(relaxed = true)
    private val usecase: GetMarketPriceChartUseCase =
        GetMarketPriceChartUseCase(repository, useCaseExecutor, postExecutionThread)

    private val params = GetMarketPriceChartUseCase.Params(TIME_SPAN)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `getChart method of repository was called`() {
        mockGetChartResponse()
        usecase.execute(params, {}, {})
        verify(exactly = 1) { repository.getChart(any(), any()) }
    }

    @Test
    fun `correct arguments were passed to the getChart method of repository`() {

        mockGetChartResponse()
        usecase.execute(params, {}, {})
        verify { repository.getChart(CHART_NAME, TIME_SPAN) }
    }

    @Test
    fun `repository completes`() {
        mockGetChartResponse()
        usecase.buildSingle(params).test().assertComplete()
    }

    @Test
    fun `repository returns data`() {
        val chart = makeChart(10)
        mockGetChartResponse(chart)
        usecase.buildSingle(params).test().assertValue(chart)
    }

    private fun mockGetChartResponse(chart: Chart = makeChart(10)) {
        every { repository.getChart(any(), any()) } returns Single.just(chart)
    }
}
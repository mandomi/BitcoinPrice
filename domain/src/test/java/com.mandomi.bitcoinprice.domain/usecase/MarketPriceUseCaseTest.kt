package com.mandomi.bitcoinprice.domain.usecase

import com.mandomi.bitcoinprice.domain.executer.PostExecutionThread
import com.mandomi.bitcoinprice.domain.executer.UseCaseExecutor
import com.mandomi.bitcoinprice.domain.factory.ChartFactory.chart
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
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

    private val params = GetMarketPriceChartUseCase.Params("1week")

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `check getChart method of repository was called`() {

        every { repository.getChart(any(), any()) } returns Single.just(chart)

        usecase.execute(params, {}, {})

        verify(exactly = 1) { repository.getChart(any(), any()) }
    }

    @Test
    fun `check correct arguments were passed to the getChart method of repository`() {

        every { repository.getChart(any(), any()) } returns Single.just(chart)

        usecase.execute(params, {}, {})

        verify { repository.getChart("market-price", "1week") }
    }
}
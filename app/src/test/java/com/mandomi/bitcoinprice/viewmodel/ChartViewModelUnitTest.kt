package com.mandomi.bitcoinprice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase
import com.mandomi.bitcoinprice.factory.ChartFactory
import com.mandomi.bitcoinprice.ui.chart.ChartViewModel
import com.mandomi.bitcoinprice.ui.model.ResourceState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChartViewModelUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMarketPriceChart: GetMarketPriceChartUseCase = mockk(relaxed = true)
    private val viewModel: ChartViewModel = ChartViewModel(getMarketPriceChart)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `getMarketPriceChartUseCase executes`() {
        viewModel.load("7days")

        verify(exactly = 1) { getMarketPriceChart.execute(any(), any(), any()) }
    }

    @Test
    fun `getMarketPriceChartUseCase returns success`() {
        viewModel.load("")
        every {
            val success = captureLambda<(result: Chart) -> Unit>()
            val error = captureLambda<(e: Throwable) -> Unit>()
            getMarketPriceChart.execute(GetMarketPriceChartUseCase.Params(), success, error)
            success.invoke(ChartFactory.makeChart(10))
        }
        assert(viewModel.getData().value?.resourceState == ResourceState.SUCCESS)
    }

}
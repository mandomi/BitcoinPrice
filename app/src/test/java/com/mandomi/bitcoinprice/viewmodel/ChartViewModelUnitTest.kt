package com.mandomi.bitcoinprice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mandomi.bitcoinprice.boundary.toChartItem
import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.faliure.Failure
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase
import com.mandomi.bitcoinprice.data.factory.ChartFactory
import com.mandomi.bitcoinprice.ui.chart.ChartViewModel
import com.mandomi.bitcoinprice.ui.model.ResourceState
import io.mockk.*
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
    fun `getMarketPriceChartUseCase success`() {
        val success = slot<(chart: Chart) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), capture(success), any())
        } answers {
            success.invoke(ChartFactory.makeChart(10))
        }

        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.SUCCESS)
    }

    @Test
    fun `getMarketPriceChartUseCase success and contains data`() {
        val chart: Chart = ChartFactory.makeChart(10)
        val success = slot<(chart: Chart) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), capture(success), any())
        } answers {
            success.invoke(chart)
        }

        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.SUCCESS)
        assert(viewModel.getData().value?.data == chart.toChartItem())
    }

    @Test
    fun `getMarketPriceChartUseCase success and contains no error`() {
        val chart: Chart = ChartFactory.makeChart(10)
        val success = slot<(chart: Chart) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), capture(success), any())
        } answers {
            success.invoke(chart)
        }

        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.SUCCESS)
        assert(viewModel.getData().value?.failure == null)
    }

    @Test
    fun `getMarketPriceChartUseCase fails`() {
        val error = slot<(throwable: Throwable) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), any(), capture(error))
        } answers {
            error.invoke(Failure.ServerError("Server Error"))
        }

        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.ERROR)
    }

    @Test
    fun `getMarketPriceChartUseCase fails and contains error data`() {
        val error = slot<(throwable: Throwable) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), any(), capture(error))
        } answers {
            error.invoke(Failure.ServerError("Server Error"))
        }

        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.ERROR)
        assert(viewModel.getData().value?.failure is Failure.ServerError)
    }

    @Test
    fun `getMarketPriceChartUseCase fails and contain no data`() {
        val error = slot<(throwable: Throwable) -> Unit>()

        every {
            getMarketPriceChart.execute(any(), any(), capture(error))
        } answers {
            error.invoke(Failure.ServerError("Server Error"))
        }

        viewModel.load("")

        assert(viewModel.getData().value?.resourceState == ResourceState.ERROR)
        assert(viewModel.getData().value?.data == null)
    }

    @Test
    fun `getMarketPriceChartUseCase returns loading`() {
        viewModel.load("")
        assert(viewModel.getData().value?.resourceState == ResourceState.LOADING)
    }

    @Test
    fun `getMarketPriceChartUseCase contains no data when loading`() {
        viewModel.load("")

        assert(viewModel.getData().value?.resourceState == ResourceState.LOADING)
        assert(viewModel.getData().value?.data == null)
    }

    @Test
    fun `getMarketPriceChartUseCase contains no error when loading`() {
        viewModel.load("")

        assert(viewModel.getData().value?.resourceState == ResourceState.LOADING)
        assert(viewModel.getData().value?.failure == null)
    }

}
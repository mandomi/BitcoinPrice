package com.mandomi.bitcoinprice.data.repository

import com.mandomi.bitcoinprice.data.factory.ChartEntityFactory
import com.mandomi.bitcoinprice.data.factory.ChartEntityFactory.CHART_NAME
import com.mandomi.bitcoinprice.data.factory.ChartEntityFactory.TIME_SPAN
import com.mandomi.bitcoinprice.data.factory.ChartEntityFactory.WRONG_CHART_NAME
import com.mandomi.bitcoinprice.data.datasource.ChartRemoteDataSource
import com.mandomi.bitcoinprice.data.reporitory.ChartRepositoryImpl
import com.mandomi.bitcoinprice.data.toChart
import com.mandomi.bitcoinprice.domain.faliure.Failure
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ChartRepositoryImplTest {

    private val dataSource: ChartRemoteDataSource = mockk()
    private val repository: ChartRepositoryImpl = ChartRepositoryImpl(dataSource)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `getChart works`() {

        every { dataSource.getChart(CHART_NAME, any()) } returns Single.just(ChartEntityFactory.chart)
        every { dataSource.getChart(WRONG_CHART_NAME, any()) } returns Single.error(Failure.NotFoundError("Not Found"))

        val successObserver = repository.getChart(CHART_NAME, TIME_SPAN).test()
        with(successObserver) {
            assertComplete()
            assertNoErrors()
            assertValue(ChartEntityFactory.chart.toChart())
        }

        repository.getChart(WRONG_CHART_NAME, TIME_SPAN).test().assertError(Failure.NotFoundError::class.java)
    }
}
package com.mandomi.bitcoinprice.mapper

import com.mandomi.bitcoinprice.boundary.toChartItem
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun `Chart is correctly mapped to ChartItem`() {
        val (input, expected) = MappersDataFactory.validDataProvider()
        assertEquals(expected, input.toChartItem())
    }
}
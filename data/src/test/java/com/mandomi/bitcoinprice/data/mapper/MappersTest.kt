package com.mandomi.bitcoinprice.data.mapper

import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.data.factory.ChartFactory
import com.mandomi.bitcoinprice.data.toChart
import com.mandomi.bitcoinprice.domain.entity.Chart
import org.junit.Assert
import org.junit.Test

class MappersTest {

    @Test
    fun `ChartEntry map to Chart`() {
        val chartEntry: ChartEntity = ChartFactory.makeChartEntity(10)
        val chart: Chart = chartEntry.toChart()

        Assert.assertEquals(chart.name, chartEntry.name)
        Assert.assertEquals(chart.unit, chartEntry.unit)
        Assert.assertEquals(chart.period, chartEntry.period)
        Assert.assertEquals(chart.description, chartEntry.description)
        Assert.assertEquals(chart.points, chartEntry.points)
    }

}
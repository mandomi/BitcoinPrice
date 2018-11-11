package com.mandomi.bitcoinprice.remote.mapper


import com.mandomi.bitcoinprice.data.entity.ChartEntity
import com.mandomi.bitcoinprice.remote.dto.ChartDTO
import com.mandomi.bitcoinprice.remote.factory.ChartFactory
import com.mandomi.bitcoinprice.remote.toChartEntity
import com.mandomi.bitcoinprice.remote.toPoint
import org.junit.Assert
import org.junit.Test

class MappersTest {

    @Test
    fun `ChartDto map to ChartEntity`() {
        val chartDTO: ChartDTO = ChartFactory.makeChartDto(10)
        val chartEntry: ChartEntity = chartDTO.toChartEntity()

        Assert.assertEquals(chartDTO.name, chartEntry.name)
        Assert.assertEquals(chartDTO.unit, chartEntry.unit)
        Assert.assertEquals(chartDTO.period, chartEntry.period)
        Assert.assertEquals(chartDTO.description, chartEntry.description)
        Assert.assertEquals(chartDTO.points.map { it.toPoint() }, chartEntry.points)
    }

}
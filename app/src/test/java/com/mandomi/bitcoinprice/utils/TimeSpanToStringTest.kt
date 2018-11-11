package com.mandomi.bitcoinprice.utils

import com.mandomi.bitcoinprice.extension.toDayMonth
import com.mandomi.bitcoinprice.extension.toDayMonthYear
import com.mandomi.bitcoinprice.extension.toMinuteDayMonthYear
import com.mandomi.bitcoinprice.extension.toMonthYear
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeSpanToStringTest {

    @Test
    fun `timespan to month and year`() {
        val data = arrayOf(
            TestData(1538956800, "Oct, 18"),
            TestData(1539388800, "Oct, 18"),
            TestData(1516406400, "Jan, 18"),
            TestData(1518134400, "Feb, 18"),
            TestData(1525824000, "May, 18"),
            TestData(1531008000, "Jul, 18")
        )

        for (item in data) {
            assertEquals(item.input.toMonthYear(), item.expected)
        }
    }

    @Test
    fun `timespan to day and month`() {
        val data = arrayOf(
            TestData(1538956800, "08. Oct"),
            TestData(1539388800, "13. Oct"),
            TestData(1516406400, "20. Jan"),
            TestData(1518134400, "09. Feb"),
            TestData(1525824000, "09. May"),
            TestData(1531008000, "08. Jul")
        )

        for (item in data) {
            assertEquals(item.input.toDayMonth(), item.expected)
        }
    }

    @Test
    fun `timespan to day, month and year`() {
        val data = arrayOf(
            TestData(1538956800, "Oct 08, 18"),
            TestData(1539388800, "Oct 13, 18"),
            TestData(1516406400, "Jan 20, 18"),
            TestData(1518134400, "Feb 09, 18"),
            TestData(1525824000, "May 09, 18"),
            TestData(1531008000, "Jul 08, 18")
        )

        for (item in data) {
            assertEquals(item.input.toDayMonthYear(), item.expected)
        }
    }

    @Test
    fun `timespan to minute, hour, day, month and year`() {
        val data = arrayOf(
            TestData(1538956800, "2018/10/08 00:00"),
            TestData(1539388800, "2018/10/13 00:00"),
            TestData(1516406400, "2018/01/20 00:00"),
            TestData(1518134400, "2018/02/09 00:00"),
            TestData(1525824000, "2018/05/09 00:00"),
            TestData(1531008000, "2018/07/08 00:00")
        )

        for (item in data) {
            assertEquals(item.input.toMinuteDayMonthYear(), item.expected)
        }
    }
}

data class TestData(val input: Long, val expected: String)
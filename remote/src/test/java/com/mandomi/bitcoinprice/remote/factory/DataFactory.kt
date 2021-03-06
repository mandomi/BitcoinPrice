package com.mandomi.bitcoinprice.remote.factory

import java.util.concurrent.ThreadLocalRandom

internal class DataFactory {

    companion object Factory {

        private fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomLong(): Long {
            return randomInt().toLong()
        }

        fun randomDouble(): Double {
            return randomInt().toDouble()
        }
    }
}
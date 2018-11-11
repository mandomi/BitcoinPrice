package com.mandomi.bitcoinprice.factory

import java.util.concurrent.ThreadLocalRandom

class DataFactory {

    companion object Factory {

        fun randomInt(): Int {
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
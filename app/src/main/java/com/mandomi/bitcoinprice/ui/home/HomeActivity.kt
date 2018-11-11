package com.mandomi.bitcoinprice.ui.home

import android.os.Bundle
import com.mandomi.bitcoinprice.R
import com.mandomi.bitcoinprice.extension.inTransaction
import com.mandomi.bitcoinprice.ui.chart.ChartFragment
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.inTransaction {
            replace(
                R.id.contentFrame,
                ChartFragment.getInstance()
            )
        }
    }
}

package com.mandomi.bitcoinprice.di.component

import com.mandomi.bitcoinprice.core.BitcoinPriceApplication
import com.mandomi.bitcoinprice.di.module.AppModule
import com.mandomi.bitcoinprice.di.module.NetworkModule
import com.mandomi.bitcoinprice.di.module.ViewModelModule
import com.mandomi.bitcoinprice.ui.chart.ChartModule
import com.mandomi.bitcoinprice.ui.home.HomeModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        NetworkModule::class,
        ChartModule::class,
        HomeModule::class
    ]
)
interface AppComponent : AndroidInjector<BitcoinPriceApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BitcoinPriceApplication>()

}
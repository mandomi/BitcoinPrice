package com.mandomi.bitcoinprice.di.component

import com.mandomi.bitcoinprice.core.BitcoinPriceApplication
import com.mandomi.bitcoinprice.di.module.AppModule
import com.mandomi.bitcoinprice.di.module.ChartModule
import com.mandomi.bitcoinprice.di.module.NetworkModule
import com.mandomi.bitcoinprice.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ChartModule::class
    ]
)
interface AppComponent : AndroidInjector<BitcoinPriceApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BitcoinPriceApplication>()

}
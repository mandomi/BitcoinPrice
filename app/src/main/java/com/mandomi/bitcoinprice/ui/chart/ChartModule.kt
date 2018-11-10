package com.mandomi.bitcoinprice.ui.chart

import androidx.lifecycle.ViewModel
import com.mandomi.bitcoinprice.data.datasource.ChartRemoteDataSource
import com.mandomi.bitcoinprice.data.reporitory.ChartRepositoryImpl
import com.mandomi.bitcoinprice.di.annotation.ViewModelKey
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
import com.mandomi.bitcoinprice.remote.datasource.ChartRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ChartModule {

    @ContributesAndroidInjector
    internal abstract fun chartFragment(): ChartFragment

    @Binds
    @IntoMap
    @ViewModelKey(ChartViewModel::class)
    abstract fun bindChartViewModel(chartViewModel: ChartViewModel): ViewModel

    @Binds
    abstract fun bindChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository

    @Binds
    abstract fun bindChartRemoteDataSource(chartRemoteDataSourceImpl: ChartRemoteDataSourceImpl): ChartRemoteDataSource
}
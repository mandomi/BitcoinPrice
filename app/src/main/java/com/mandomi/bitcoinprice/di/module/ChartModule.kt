package com.mandomi.bitcoinprice.di.module

import com.mandomi.bitcoinprice.data.datasource.ChartRemoteDataSource
import com.mandomi.bitcoinprice.data.reporitory.ChartRepositoryImpl
import com.mandomi.bitcoinprice.domain.repository.ChartRepository
import com.mandomi.bitcoinprice.remote.datasource.ChartRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ChartModule {

    @Binds
    abstract fun bindChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository

    @Binds
    abstract fun bindChartRemoteDataSource(chartRemoteDataSourceImpl: ChartRemoteDataSourceImpl): ChartRemoteDataSource
}
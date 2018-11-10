package com.mandomi.bitcoinprice.di.module

import android.content.Context
import com.mandomi.bitcoinprice.core.BitcoinPriceApplication
import com.mandomi.bitcoinprice.domain.executer.PostExecutionThread
import com.mandomi.bitcoinprice.domain.executer.UseCaseExecutor
import com.mandomi.bitcoinprice.executer.ExecutorThread
import com.mandomi.bitcoinprice.executer.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

//    @Provides
//    @Singleton
//    fun provideApplicationContext(application: BitcoinPriceApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor = ExecutorThread()

    @Provides
    @Singleton
    fun providePostExecutionThread(): PostExecutionThread = UIThread()
}
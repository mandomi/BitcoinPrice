package com.mandomi.bitcoinprice.di.module

import androidx.lifecycle.ViewModelProvider
import com.mandomi.bitcoinprice.core.ViewModelFactory
import dagger.Binds

import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
package com.farsitel.bazaar.ui.base

import androidx.lifecycle.ViewModelProvider
import dagger.android.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
}
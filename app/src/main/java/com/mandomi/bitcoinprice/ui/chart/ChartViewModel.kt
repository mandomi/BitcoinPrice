package com.mandomi.bitcoinprice.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mandomi.bitcoinprice.boundary.toChartItem
import com.mandomi.bitcoinprice.domain.entity.Chart
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase
import com.mandomi.bitcoinprice.domain.interactor.GetMarketPriceChartUseCase.Params
import com.mandomi.bitcoinprice.ui.base.BaseViewModel
import com.mandomi.bitcoinprice.ui.model.Resource
import com.mandomi.bitcoinprice.ui.model.ResourceState
import javax.inject.Inject

class ChartViewModel @Inject constructor(private val marketPriceChartUseCase: GetMarketPriceChartUseCase) :
    BaseViewModel() {

    private val data: MutableLiveData<Resource<ChartItem>> = MutableLiveData()

    fun getData(): LiveData<Resource<ChartItem>> = data

    fun load(duration: String) {
        data.value = Resource(ResourceState.LOADING)
        useCases += marketPriceChartUseCase.execute(Params(timeSpan = duration), ::success, ::error)
    }

    private fun success(chart: Chart) {
        data.value = Resource(ResourceState.SUCCESS, chart.toChartItem())
    }

    private fun error(throwable: Throwable) {
        data.value = Resource(ResourceState.ERROR, failure = throwable)
    }

}
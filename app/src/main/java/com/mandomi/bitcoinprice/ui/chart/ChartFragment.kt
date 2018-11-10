package com.mandomi.bitcoinprice.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mandomi.bitcoinprice.R
import com.mandomi.bitcoinprice.extension.createViewModel
import com.mandomi.bitcoinprice.extension.observe
import com.mandomi.bitcoinprice.ui.base.BaseFragment
import com.mandomi.bitcoinprice.ui.model.Resource
import com.mandomi.bitcoinprice.ui.model.ResourceState

class ChartFragment : BaseFragment() {

    companion object {
        fun getInstance() = ChartFragment()
    }

    private lateinit var viewModel: ChartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = createViewModel(viewModelFactory) {
            observe(getData(), ::handleStates)
        }
    }

    private fun handleStates(resource: Resource<ChartItem>?) {
        resource?.let {
            when (resource.resourceState) {
                ResourceState.LOADING -> handleLoading()
                ResourceState.SUCCESS -> handleSuccess(resource.data!!)
                ResourceState.ERROR -> handleError(resource.failure!!)
            }
        }
    }

    private fun handleLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleSuccess(data: ChartItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleError(failure: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

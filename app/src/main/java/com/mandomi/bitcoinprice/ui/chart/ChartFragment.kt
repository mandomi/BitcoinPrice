package com.mandomi.bitcoinprice.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.snackbar.Snackbar
import com.mandomi.bitcoinprice.R
import com.mandomi.bitcoinprice.domain.faliure.Failure
import com.mandomi.bitcoinprice.extension.createViewModel
import com.mandomi.bitcoinprice.extension.observe
import com.mandomi.bitcoinprice.extension.toDayMonthYear
import com.mandomi.bitcoinprice.ui.base.BaseFragment
import com.mandomi.bitcoinprice.ui.model.Resource
import com.mandomi.bitcoinprice.ui.model.ResourceState
import com.mandomi.bitcoinprice.ui.widget.CustomMarkerView
import kotlinx.android.synthetic.main.fragment_chart.*
import java.util.*

class ChartFragment : BaseFragment(), AdapterView.OnItemSelectedListener {
    companion object {
        fun getInstance() = ChartFragment()
    }

    private lateinit var viewModel: ChartViewModel
    private var lastDuration: ChartDataDuration = ChartDataDuration.ONE_WEEK

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = createViewModel(viewModelFactory) {
            observe(getData(), ::handleStates)
        }
        viewModel.load(lastDuration.value)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
        createDurationSpinner()
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

    private fun setupChart() {

        with(chart) {
            setBackgroundColor(Color.WHITE)
            setTouchEnabled(true)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            legend.isEnabled = false
            description.isEnabled = false
            animateX(1500)
            marker = CustomMarkerView(requireContext(), R.layout.custom_marker_view).apply { chartView = chart }

            with(xAxis) {
                valueFormatter = IAxisValueFormatter { value, _ -> value.toLong().toDayMonthYear() }
                disableAxisLineDashedLine()
                disableGridDashedLine()
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setCenterAxisLabels(true)
                position = XAxis.XAxisPosition.BOTTOM
                removeAllLimitLines()
                textSize = 12f
                granularity = 1f
                labelCount = 1
                animateX(1500)
            }

            with(axisLeft) {
                disableGridDashedLine()
                disableAxisLineDashedLine()
            }

            axisRight.isEnabled = false
        }
    }

    private fun createDurationSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.duration_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            with(durationSpinner) {
                this.adapter = adapter
                onItemSelectedListener = this@ChartFragment
            }
        }
    }

    private fun handleLoading() {
        showLoading()
        hideChartViews()
        hideErrorView()
        hideEmptyView()
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideChartViews() {
        chartGroup.visibility = View.GONE
    }

    private fun hideErrorView() {
        errorView.visibility = View.GONE
    }

    private fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    private fun handleSuccess(data: ChartItem) {
        hideLoading()
        showChartViews()
        hideErrorView()
        hideEmptyView()
        showData(data)
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showChartViews() {
        chartGroup.visibility = View.VISIBLE
    }

    private fun showData(data: ChartItem) {

        chartName.text = data.name
        chartDescription.text = data.description

        val values = mutableListOf<Entry>()
        for (point in data.points) {
            values.add(Entry(point.timeSpan.toFloat(), point.value.toFloat()))
        }

        data.minValue?.let { chart.axisLeft.axisMinimum = it - 50 }
        data.maxValue?.let { chart.axisLeft.axisMaximum = it + 50 }

        val lineDataSet: LineDataSet

        if (chart.data != null && chart.data.dataSetCount > 0) run {
            lineDataSet = chart.data.getDataSetByIndex(0) as LineDataSet

            with(lineDataSet) {
                this.values = values
                notifyDataSetChanged()
            }

            with(chart) {
                this.data.notifyDataChanged()
                notifyDataSetChanged()
            }

        } else {
            lineDataSet = LineDataSet(values, null)

            with(lineDataSet) {
                setDrawIcons(false)
                disableDashedLine()
                disableDashedHighlightLine()
                setDrawCircleHole(false)
                setDrawFilled(false)
                setDrawValues(false)

                color = Color.BLUE
                setCircleColor(Color.BLUE)

                lineWidth = 1f
                circleRadius = 3f
            }

            val dataSet = ArrayList<ILineDataSet>()
            dataSet.add(lineDataSet)

            val lineData = LineData(dataSet)
            chart.data = lineData
        }
    }

    private fun handleError(failure: Throwable) {
        hideLoading()
        hideChartViews()
        if (failure is Failure.NotFoundError) {
            hideErrorView()
            showEmptyView()
        } else {
            hideEmptyView()
            showErrorView()
            showSnackBar(failure.message)
        }
    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun showErrorView() {
        errorView.visibility = View.VISIBLE
    }

    private fun showSnackBar(message: String?) {
        message?.let {
            Snackbar.make(view!!, message, Snackbar.LENGTH_INDEFINITE).apply {
                view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
                    setAction(getString(R.string.try_again)) { viewModel.load(lastDuration.value) }
                }
            }.show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        val selectedDuration = ChartDataDuration.values()[pos]
        if (selectedDuration != lastDuration) {
            lastDuration = selectedDuration
            viewModel.load(lastDuration.value)
        }
    }

}

package com.mandomi.bitcoinprice.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.mandomi.bitcoinprice.R
import com.mandomi.bitcoinprice.extension.*
import com.mandomi.bitcoinprice.ui.base.BaseFragment
import com.mandomi.bitcoinprice.ui.model.Resource
import com.mandomi.bitcoinprice.ui.model.ResourceState
import kotlinx.android.synthetic.main.fragment_chart.*
import java.util.*

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
        viewModel.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
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

            with(xAxis) {
                valueFormatter = IAxisValueFormatter { value, axis -> value.toLong().toDayMonthYear() }
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
            }

            with(axisLeft) {
                disableGridDashedLine()
                disableAxisLineDashedLine()
                yOffset = -9f

            }

            axisRight.isEnabled = false
        }
    }

    private fun handleLoading() {
        showLoading()
        hideChartViews()
    }

    private fun hideChartViews() {
        chartGroup.visibility = View.GONE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun handleSuccess(data: ChartItem) {
        hideLoading()
        showChartViews()
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

            data.minValue?.let { chart.axisLeft.axisMinimum = it - 50 }
            data.maxValue?.let { chart.axisLeft.axisMaximum = it + 50 }

            val lineData = LineData(dataSet)
            chart.data = lineData
        }
    }

    private fun handleError(failure: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

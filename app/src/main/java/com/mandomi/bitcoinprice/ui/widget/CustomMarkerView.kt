package com.mandomi.bitcoinprice.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.custom_marker_view.view.*

@SuppressLint("ViewConstructor")
class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e is CandleEntry) {
            val ce = e as CandleEntry?
            value.text = Utils.formatNumber(ce!!.high, 0, true)
        } else {
            value.text = Utils.formatNumber(e!!.y, 0, true)
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}

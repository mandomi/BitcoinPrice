package com.mandomi.bitcoinprice.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.mandomi.bitcoinprice.extension.toMinuteDayMonthYear
import kotlinx.android.synthetic.main.custom_marker_view.view.*

@SuppressLint("ViewConstructor")
class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    override fun refreshContent(entry: Entry, highlight: Highlight?) {
        if (entry is CandleEntry) {
            value.text = Utils.formatNumber(entry.high, 0, true)
            date.text = entry.low.toLong().toMinuteDayMonthYear()
        } else {
            value.text = Utils.formatNumber(entry.y, 0, true)
            date.text = entry.x.toLong().toMinuteDayMonthYear()
        }
        super.refreshContent(entry, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}

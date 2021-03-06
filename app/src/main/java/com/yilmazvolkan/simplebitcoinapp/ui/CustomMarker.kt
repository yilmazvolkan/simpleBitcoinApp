package com.yilmazvolkan.simplebitcoinapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.collections.ArrayList
import kotlin.math.round
import kotlinx.android.synthetic.main.marker_view.view.tvDate
import kotlinx.android.synthetic.main.marker_view.view.tvPrice

@SuppressLint("ViewConstructor")
class CustomMarker(context: Context, layoutResource: Int, private val dates: ArrayList<Long>) :
    MarkerView(context, layoutResource) {
    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        val value = entry?.y?.toDouble() ?: 0.0
        tvPrice.text = value.round(4).toString()
        tvDate.text = entry?.x?.let { convertToDate(dates[it.toInt()]) }
        super.refreshContent(entry, highlight)
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
        return MPPointF(getXOffset(xpos), -height - 10f)
    }

    private fun getXOffset(xPos: Float): Float {
        // This will center the marker-view horizontally
        val minOffset = 50
        if (xPos < minOffset) return 0f
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        // For right hand side
        if (metrics.widthPixels - xPos < minOffset) return -width.toFloat() else if (metrics.widthPixels - xPos < 0) return -width.toFloat()
        return -(width / 2).toFloat()
    }

    private fun convertToDate(unixSeconds: Long): String {
        val date = Date(unixSeconds * 1000L)
        val sdf = SimpleDateFormat("dd-MM", Locale.getDefault())
        return sdf.format(date).toString()
    }
}

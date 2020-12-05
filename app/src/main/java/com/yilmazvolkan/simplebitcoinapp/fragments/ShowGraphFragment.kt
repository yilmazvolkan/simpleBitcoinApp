package com.yilmazvolkan.simplebitcoinapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.databinding.FragmentShowGraphBinding
import com.yilmazvolkan.simplebitcoinapp.ui.CustomMarker
import com.yilmazvolkan.simplebitcoinapp.util.inflate

class ShowGraphFragment : Fragment() {

    private val binding: FragmentShowGraphBinding by inflate(R.layout.fragment_show_graph)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = ArrayList<Entry>()


        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

        entries.add(Entry(6f, 10f))
        entries.add(Entry(7f, 2f))
        entries.add(Entry(8f, 7f))
        entries.add(Entry(9f, 20f))
        entries.add(Entry(10f, 16f))


        entries.add(Entry(11f, 10f))
        entries.add(Entry(12f, 2f))
        entries.add(Entry(13f, 7f))
        entries.add(Entry(14f, 20f))
        entries.add(Entry(15f, 16f))


        val vl = LineDataSet(entries, "My Type")

        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 2f
        vl.fillColor = R.color.teal_700
        vl.fillAlpha = R.color.purple_500


        binding.lineChart.xAxis.labelRotationAngle = 0f


        binding.lineChart.data = LineData(vl)


        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.axisMaximum = 30+0.1f


        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)


        binding.lineChart.description.text = "Days"
        binding.lineChart.setNoDataText("No bitcoin data yet!")


        val markerView = CustomMarker(requireContext(), R.layout.marker_view)
        binding.lineChart.marker = markerView
    }

    companion object {
        fun newInstance(): ShowGraphFragment {
            return ShowGraphFragment()
        }
    }
}
package com.yilmazvolkan.simplebitcoinapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.yilmazvolkan.simplebitcoinapp.BitcoinApplication
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.databinding.FragmentShowGraphBinding
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData
import com.yilmazvolkan.simplebitcoinapp.ui.CustomMarker
import com.yilmazvolkan.simplebitcoinapp.ui.inflate
import com.yilmazvolkan.simplebitcoinapp.viewModels.CoinViewModel
import com.yilmazvolkan.simplebitcoinapp.viewModels.CoinViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ShowGraphFragment : Fragment() {

    @Inject
    lateinit var bitcoinApplication: BitcoinApplication

    private val binding: FragmentShowGraphBinding by inflate(R.layout.fragment_show_graph)
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.create().inject(this)

        initializeViewModel()
        observeCoinViewModel()
    }

    private fun initializeViewModel() {
        activity?.let {
            coinViewModel = ViewModelProvider(
                this,
                CoinViewModelFactory(bitcoinApplication)
            ).get(CoinViewModel::class.java)
        }
    }


    private fun observeCoinViewModel() = with(coinViewModel) {
        repository.isInProgress.observe(viewLifecycleOwner, { isLoading ->
            isLoading.let {
                if (it) {
                    binding.lineChart.visibility = View.GONE
                    binding.fetchProgress.visibility = View.VISIBLE
                } else {
                    binding.fetchProgress.visibility = View.GONE
                }
            }
        })
        repository.isError.observe(viewLifecycleOwner, { isError ->
            isError.let {
                if (it) {
                    // TODO disableViewsOnError()
                } else {
                    binding.fetchProgress.visibility = View.VISIBLE
                }
            }
        })
        repository.bitcoinData.observe(viewLifecycleOwner, { coinValues ->
            coinValues.let {
                if (it != null && it.isNotEmpty()) {
                    binding.fetchProgress.visibility = View.VISIBLE
                    binding.lineChart.visibility = View.VISIBLE
                    updateTodayDate()
                    loadDataIntoChart(it)
                    binding.fetchProgress.visibility = View.GONE
                } else {
                    // TODO disableViewsOnError()
                }
            }
        })
    }

    private fun updateTodayDate() {
        val currentTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

        val sharedPreference =
            requireContext().getSharedPreferences("LAST_UPDATE", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("UPDATE_DAY", formatter.format(currentTime))
        editor.apply()
    }

    private fun loadDataIntoChart(coinList: List<BitcoinData>) {
        val entries = ArrayList<Entry>()

        for ((index, value) in coinList.withIndex()) {
            entries.add(Entry(index.toFloat(), value.y))
        }

        val lineDataSet = LineDataSet(entries, "Bitcoin")

        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawCircles(false)
        lineDataSet.lineWidth = 2f
        lineDataSet.fillColor = ContextCompat.getColor(requireContext(), R.color.chart_background)
        lineDataSet.fillAlpha = ContextCompat.getColor(requireContext(), R.color.chart_line)

        binding.lineChart.xAxis.labelRotationAngle = 0f


        binding.lineChart.data = LineData(lineDataSet)


        //todo ayrı methoda al
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.isEnabled = false
        binding.lineChart.axisLeft.setDrawGridLines(false)
        binding.lineChart.axisLeft.textColor =
            ContextCompat.getColor(requireContext(), R.color.gray)
        binding.lineChart.axisLeft.axisLineColor =
            ContextCompat.getColor(requireContext(), R.color.gray)
        binding.lineChart.legend.textColor = ContextCompat.getColor(requireContext(), R.color.gray)
        binding.lineChart.xAxis.axisMaximum = coinList.size + 0.5f


        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)


        binding.lineChart.description.text = "Days"
        binding.lineChart.description.textColor =
            ContextCompat.getColor(requireContext(), R.color.gray)
        binding.lineChart.setNoDataText("No coin data yet!")


        val markerView = CustomMarker(requireContext(), R.layout.marker_view)
        markerView.chartView = binding.lineChart // For bounds control
        binding.lineChart.marker = markerView
    }

    companion object {
        fun newInstance(): ShowGraphFragment {
            return ShowGraphFragment()
        }
    }
}
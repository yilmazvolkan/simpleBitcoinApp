package com.yilmazvolkan.simplebitcoinapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.databinding.FragmentShowGraphBinding
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData
import com.yilmazvolkan.simplebitcoinapp.ui.CustomMarker
import com.yilmazvolkan.simplebitcoinapp.util.CoinViewModelFactory
import com.yilmazvolkan.simplebitcoinapp.util.inflate
import com.yilmazvolkan.simplebitcoinapp.viewModels.CoinViewModel

class ShowGraphFragment : Fragment() {

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
                CoinViewModelFactory(it.application)
            ).get(CoinViewModel::class.java)
        }
    }


    private fun observeCoinViewModel() = with(coinViewModel) {
        repository.isInProgress.observe(viewLifecycleOwner, { isLoading ->
            isLoading.let {
                if (it) {
                    binding.linearLayoutChart.visibility = View.GONE
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
        repository.bitcoinData.observe(viewLifecycleOwner, { giphies ->
            giphies.let {
                if (it != null && it.isNotEmpty()) {
                    binding.fetchProgress.visibility = View.VISIBLE
                    binding.linearLayoutChart.visibility = View.VISIBLE
                    loadDataIntoChart(it)
                    binding.fetchProgress.visibility = View.GONE
                } else {
                    // TODO disableViewsOnError()
                }
            }
        })
    }


    private fun loadDataIntoChart(coinList: List<BitcoinData>) {

        val entries = ArrayList<Entry>()

        Log.d("volkan", coinList.size.toString())
        for ((i,e) in coinList.withIndex()) {
            Log.d("volkan", e.y.toString())
            entries.add(Entry(i.toFloat(), e.y))
        }

        val vl = LineDataSet(entries, "My Type")

        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 2f
        vl.fillColor = R.color.teal_700
        vl.fillAlpha = R.color.purple_500


        binding.lineChart.xAxis.labelRotationAngle = 0f


        binding.lineChart.data = LineData(vl)


        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.axisMaximum = coinList.size + 0.5f


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
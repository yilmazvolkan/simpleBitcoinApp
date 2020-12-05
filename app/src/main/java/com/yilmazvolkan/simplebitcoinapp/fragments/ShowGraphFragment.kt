package com.yilmazvolkan.simplebitcoinapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.databinding.FragmentShowGraphBinding
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

    }
}
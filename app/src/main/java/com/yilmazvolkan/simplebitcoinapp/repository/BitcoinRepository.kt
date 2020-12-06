package com.yilmazvolkan.simplebitcoinapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApi
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData
import javax.inject.Inject

class BitcoinRepository {

    @Inject
    lateinit var bitcoinApiService: BitcoinApi

    private val _bitcoinData by lazy { MutableLiveData<List<BitcoinData>>() }
    val bitcoinData: LiveData<List<BitcoinData>>
        get() = _bitcoinData

    private val _isInProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isInProgress

    private val _isError by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean>
        get() = _isError


    init {
        DaggerAppComponent.create().inject(this)
    }
}
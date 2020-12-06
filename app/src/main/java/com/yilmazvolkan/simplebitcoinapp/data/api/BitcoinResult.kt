package com.yilmazvolkan.simplebitcoinapp.data.api

import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData

data class BitcoinResult(
    val status: String,
    val period: String,
    val values: List<BitcoinData>
)

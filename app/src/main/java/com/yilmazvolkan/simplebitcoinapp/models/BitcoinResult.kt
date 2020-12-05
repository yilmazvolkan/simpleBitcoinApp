package com.yilmazvolkan.simplebitcoinapp.models

data class BitcoinResult(
    val status: String,
    val period: String,
    val values: List<BitcoinData>
    )

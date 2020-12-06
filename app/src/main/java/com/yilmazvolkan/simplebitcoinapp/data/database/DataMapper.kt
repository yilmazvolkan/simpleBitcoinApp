package com.yilmazvolkan.simplebitcoinapp.data.database

import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData

fun DataEntity.toData() = BitcoinData(
    this.x,
    this.y
)

fun List<DataEntity>.toDataList() = this.map { it.toData() }

fun BitcoinData.toDataEntity() = DataEntity(
    x = this.x,
    y = this.y
)

fun List<BitcoinData>.toDataEntityList() = this.map { it.toDataEntity() }
package com.yilmazvolkan.simplebitcoinapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "timestamp")
    val x: Int,
    @ColumnInfo(name = "value")
    val y: Float
)



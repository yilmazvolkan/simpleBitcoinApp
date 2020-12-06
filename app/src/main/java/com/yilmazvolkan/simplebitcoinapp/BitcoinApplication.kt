package com.yilmazvolkan.simplebitcoinapp

import android.app.Application
import com.yilmazvolkan.simplebitcoinapp.data.database.BitcoinDatabase

class BitcoinApplication : Application() {

    companion object {
        lateinit var instance: BitcoinApplication
        lateinit var database: BitcoinDatabase
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = BitcoinDatabase.invoke(this)
    }
}
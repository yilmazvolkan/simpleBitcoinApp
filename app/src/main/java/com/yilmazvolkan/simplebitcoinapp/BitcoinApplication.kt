package com.yilmazvolkan.simplebitcoinapp

import android.app.Application

class BitcoinApplication : Application() {

    companion object {
        lateinit var instance: BitcoinApplication
    }

    init {
        instance = this
    }
}

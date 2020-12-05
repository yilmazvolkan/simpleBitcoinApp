package com.yilmazvolkan.simplebitcoinapp

import com.yilmazvolkan.simplebitcoinapp.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BitcoinApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out BitcoinApplication?> {
        return DaggerAppComponent.builder().create(this)
    }
}
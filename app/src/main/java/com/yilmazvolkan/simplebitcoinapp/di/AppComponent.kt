package com.yilmazvolkan.simplebitcoinapp.di

import com.yilmazvolkan.simplebitcoinapp.repository.BitcoinRepository
import com.yilmazvolkan.simplebitcoinapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(bitcoinRepository: BitcoinRepository)

    fun inject(mainActivity: MainActivity)
}
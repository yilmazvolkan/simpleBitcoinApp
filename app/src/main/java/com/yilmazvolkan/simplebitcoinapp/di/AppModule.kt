package com.yilmazvolkan.simplebitcoinapp.di

import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApi
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApiService
import com.yilmazvolkan.simplebitcoinapp.repository.BitcoinRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): BitcoinApi = BitcoinApiService.getClient()

    @Provides
    fun provideBitcoinRepository() = BitcoinRepository()
}
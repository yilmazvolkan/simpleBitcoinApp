package com.yilmazvolkan.simplebitcoinapp.di

import com.yilmazvolkan.simplebitcoinapp.BitcoinApplication
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApi
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApiService
import com.yilmazvolkan.simplebitcoinapp.data.database.BitcoinDatabase
import com.yilmazvolkan.simplebitcoinapp.models.DateModel
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

    @Singleton
    @Provides
    fun provideApplication(): BitcoinApplication = BitcoinApplication.instance

    @Singleton
    @Provides
    fun provideDB(application: BitcoinApplication) = BitcoinDatabase.invoke(application)

    @Provides
    fun provideDao(bitcoinDatabase: BitcoinDatabase) = bitcoinDatabase.dataDao()

    @Provides
    fun provideDateModel() = DateModel()
}
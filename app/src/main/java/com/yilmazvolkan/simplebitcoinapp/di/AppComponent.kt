package com.yilmazvolkan.simplebitcoinapp.di

import com.yilmazvolkan.simplebitcoinapp.fragments.ShowGraphFragment
import com.yilmazvolkan.simplebitcoinapp.repository.BitcoinRepository
import com.yilmazvolkan.simplebitcoinapp.viewModels.CoinViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(bitcoinRepository: BitcoinRepository)

    fun inject(viewModel: CoinViewModel)

    fun inject(showGraphFragment: ShowGraphFragment)
}
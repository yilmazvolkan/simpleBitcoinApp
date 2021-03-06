package com.yilmazvolkan.simplebitcoinapp.viewModels

import androidx.lifecycle.ViewModel
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.repository.BitcoinRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CoinViewModel : ViewModel() {

    @Inject
    lateinit var repository: BitcoinRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
        compositeDisposable.add(repository.fetchDataFromDatabase())
    }

    // Refresh data
    fun checkDataAgain() {
        compositeDisposable.clear()
        compositeDisposable.add(repository.fetchDataFromDatabase())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

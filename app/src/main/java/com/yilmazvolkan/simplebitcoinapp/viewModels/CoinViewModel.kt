package com.yilmazvolkan.simplebitcoinapp.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.repository.BitcoinRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CoinViewModel(app: Application) : ViewModel() {

    @Inject
    lateinit var repository: BitcoinRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
        compositeDisposable.add(repository.fetchDataFromDatabase())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
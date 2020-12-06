package com.yilmazvolkan.simplebitcoinapp.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yilmazvolkan.simplebitcoinapp.viewModels.CoinViewModel

class CoinViewModelFactory(val app: Application) :
    ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            return CoinViewModel(app) as T
        }
        return super.create(modelClass)
    }
}
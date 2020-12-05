package com.yilmazvolkan.simplebitcoinapp.ui.main

import com.yilmazvolkan.simplebitcoinapp.data.ApiService
import javax.inject.Inject


class MainPresenterImpl @Inject constructor(var mainView: MainView, var apiService: ApiService) :
    MainPresenter {
    override fun loadMain() {
        apiService.loadData()
        mainView.onMainLoaded()
    }
}
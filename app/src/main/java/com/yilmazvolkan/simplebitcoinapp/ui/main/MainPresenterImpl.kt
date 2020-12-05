package com.yilmazvolkan.simplebitcoinapp.ui.main

import javax.inject.Inject


class MainPresenterImpl @Inject constructor(var mainView: MainView) :
    MainPresenter {
    override fun loadMain() {
        mainView.onMainLoaded()
    }
}
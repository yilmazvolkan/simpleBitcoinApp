package com.yilmazvolkan.simplebitcoinapp.dagger

import com.yilmazvolkan.simplebitcoinapp.ui.main.MainActivity
import com.yilmazvolkan.simplebitcoinapp.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity?
}
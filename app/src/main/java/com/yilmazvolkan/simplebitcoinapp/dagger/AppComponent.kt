package com.yilmazvolkan.simplebitcoinapp.dagger

import com.yilmazvolkan.simplebitcoinapp.BitcoinApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<BitcoinApplication?> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BitcoinApplication?>()
}
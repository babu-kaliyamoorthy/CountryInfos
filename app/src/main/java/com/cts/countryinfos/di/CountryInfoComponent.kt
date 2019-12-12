package com.cts.countryinfos.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface CountryInfoComponent {

    @Component.Builder
    interface Builder {
        fun build(): CountryInfoComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }

}
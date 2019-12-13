package com.cts.countryinfos.base

import androidx.lifecycle.ViewModel
import com.cts.countryinfos.di.CountryInfoComponent
import com.cts.countryinfos.di.DaggerCountryInfoComponent
import com.cts.countryinfos.di.NetworkModule
import com.cts.countryinfos.ui.CountryInfoListViewModel

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
abstract class CountryInfoBaseViewModel : ViewModel() {
    private val countryInfoComponent: CountryInfoComponent = DaggerCountryInfoComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CountryInfoListViewModel -> countryInfoComponent.inject(this)
        }
    }
}
package com.cts.countryinfos.ui

import com.cts.countryinfos.base.CountryInfoBaseViewModel
import com.cts.countryinfos.network.CountryInfoApi
import javax.inject.Inject

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoListViewModel : CountryInfoBaseViewModel() {

    @Inject
    lateinit var countryInfoApi: CountryInfoApi

    init {
        fetchCountryInfoList()
    }

    fun fetchCountryInfoList() {
        //
        //var countryInfo = countryInfoApi.getCountryInfo()
    }

    override fun onCleared() {
        super.onCleared()
    }
}
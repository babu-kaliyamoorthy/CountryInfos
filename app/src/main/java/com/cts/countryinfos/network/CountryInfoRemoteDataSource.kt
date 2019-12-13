package com.cts.countryinfos.network

import com.cts.countryinfos.model.Country
import com.cts.countryinfos.repository.ApiResponse

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
interface CountryInfoRemoteDataSource {
    suspend fun getCountryInfos(): ApiResponse<Country>

}
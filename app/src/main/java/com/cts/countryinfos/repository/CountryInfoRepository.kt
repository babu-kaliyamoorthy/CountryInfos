package com.cts.countryinfos.repository

import com.cts.countryinfos.model.Country
import com.cts.countryinfos.network.CountryInfoRemoteDataSource

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoRepository(private val countryInfoRemoteDataSource: CountryInfoRemoteDataSource) {

    suspend fun getCountryInfos(): ApiResponse<Country> {
        return countryInfoRemoteDataSource.getCountryInfos()
    }

    class CountryFetchError(cause: Throwable) : Throwable(cause.message, cause)

    companion object {
        private var INSTANCE: CountryInfoRepository? = null

        @JvmStatic
        fun getInstance(countryInfoRemoteDataSource: CountryInfoRemoteDataSource): CountryInfoRepository {
            return INSTANCE ?: CountryInfoRepository(countryInfoRemoteDataSource).apply {
                INSTANCE = this
            }
        }

    }
}
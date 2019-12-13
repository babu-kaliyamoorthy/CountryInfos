package com.cts.countryinfos.repository

import androidx.lifecycle.MutableLiveData
import com.cts.countryinfos.model.Country
import com.cts.countryinfos.network.CountryInfoRemoteDataSource
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoRepository(val countryInfoRemoteDataSource: CountryInfoRemoteDataSource) {

    val country: MutableLiveData<Country> by lazy {
        MutableLiveData<Country>()
    }

    suspend fun getCountryInfos() {
        withContext(Dispatchers.IO) {
            try {
                val response = countryInfoRemoteDataSource.getCountryInfos()

                if (response is ApiResponse.SuccessResponse) {
                    country.postValue(response.data)
                }

            } catch (exception: RemoteDataNotFoundException) {
                throw  CountryFetchError(exception)
            }
        }
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

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
package com.cts.countryinfos.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cts.countryinfos.model.Country
import com.cts.countryinfos.model.Info
import com.cts.countryinfos.network.CountryInfoRemoteDataSource
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoRepository(val countryInfoRemoteDataSource: CountryInfoRemoteDataSource) {

    val country: MutableLiveData<Country> by lazy {
        MutableLiveData<Country>()
    }

    val rows: MutableLiveData<List<Info>> by lazy {
        MutableLiveData<List<Info>>()
    }

    suspend fun getCountryInfos() {
        withContext(Dispatchers.IO) {
            try {
                val response = countryInfoRemoteDataSource.getCountryInfos()

                if (response is ApiResponse.SuccessResponse) {
                    country.postValue(response.data)
                    rows.postValue(response.data.rows)
                    Log.i("CountryInfoRepository", "json data is " + Gson().toJson(response.data))
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

    }
}
package com.cts.countryinfos.network

import com.cts.countryinfos.model.Country
import com.cts.countryinfos.repository.ApiResponse
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountrtyInfoRemoteDataSourceImpl private constructor(val countryInfoApiService: CountryInfoApiService) :
    CountryInfoRemoteDataSource {

    override suspend fun getCountryInfos(): ApiResponse<Country> = withContext(Dispatchers.IO) {
        val request = countryInfoApiService.getCountryInfo()
        try {
            val response = request.await()
            if (response?.rows?.isNotEmpty()!!) {
                ApiResponse.SuccessResponse(response)
            } else {
                ApiResponse.ErrorResponse(RemoteDataNotFoundException())
            }
        } catch (ex: HttpException) {
            ApiResponse.ErrorResponse(RemoteDataNotFoundException())
        } catch (ex: Throwable) {
            ApiResponse.ErrorResponse(RemoteDataNotFoundException())
        }

    }

    companion object {
        fun newInstance() = CountrtyInfoRemoteDataSourceImpl(CountryInfoApiService.create())
    }
}
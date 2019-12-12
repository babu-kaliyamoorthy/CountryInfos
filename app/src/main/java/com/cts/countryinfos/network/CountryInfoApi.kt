package com.cts.countryinfos.network

import com.cts.countryinfos.model.Country
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
interface CountryInfoApi {

    @GET("/s/2iodh4vg0eortkl/facts.js")
    suspend fun getCountryInfo(): Response<Country?>
}
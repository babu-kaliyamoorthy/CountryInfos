package com.cts.countryinfos.network

import com.cts.countryinfos.model.Country
import com.cts.countryinfos.utils.COUNTRY_INFO_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
interface CountryInfoApiService {

    @GET("/s/2iodh4vg0eortkl/facts.js")
    fun getCountryInfo(): Deferred<Country?>

    companion object {
        fun create(): CountryInfoApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(COUNTRY_INFO_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CountryInfoApiService::class.java)
        }
    }
}
package com.cts.countryinfos.di

import com.cts.countryinfos.network.CountryInfoApiService
import com.cts.countryinfos.utils.COUNTRY_INFO_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCountryInfoApi(retrofit: Retrofit): CountryInfoApiService {
        return retrofit.create(CountryInfoApiService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_INFO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
package com.cts.countryinfos.di

import com.cts.countryinfos.network.CountryInfoApi
import com.cts.countryinfos.utils.COUNTRY_INFO_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCountryInfoApi(retrofit: Retrofit): CountryInfoApi {
        return retrofit.create(CountryInfoApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_INFO_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
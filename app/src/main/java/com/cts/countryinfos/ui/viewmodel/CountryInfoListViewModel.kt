package com.cts.countryinfos.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cts.countryinfos.model.Country
import com.cts.countryinfos.repository.ApiResponse
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.viewmodel.viewModelFactory
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoListViewModel(private val countryInfoRepository: CountryInfoRepository) :
    ViewModel() {

    companion object {
        val FACTORY = viewModelFactory(::CountryInfoListViewModel)
    }

    private var _isFetchInProgress: MutableLiveData<Boolean> = MutableLiveData()
    val isFetchInProgress: LiveData<Boolean>
        get() = _isFetchInProgress

    val country: MutableLiveData<Country> by lazy {
        MutableLiveData<Country>()
    }


    fun fetchCountryInfoList() {
        fetchCountryInfos {
            getCountryInfoList()
        }
    }

    private suspend fun getCountryInfoList() {

        withContext(Dispatchers.IO) {
            try {
                val response = countryInfoRepository.getCountryInfos()

                if (response is ApiResponse.SuccessResponse) {
                    country.postValue(response.data)
                    Log.i("CountryInfoRepository", "json data is " + Gson().toJson(response.data))
                }

            } catch (exception: RemoteDataNotFoundException) {
                throw  CountryInfoRepository.CountryFetchError(exception)
            }
        }
    }


    fun fetchCountryInfos(block: suspend () -> Unit): Job {

        return viewModelScope.launch {
            try {
                _isFetchInProgress.value = true

                block()
            } catch (error: RemoteDataNotFoundException) {
            } finally {
                _isFetchInProgress.value = false
            }
        }
    }


}
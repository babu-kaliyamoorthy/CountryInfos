package com.cts.countryinfos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cts.countryinfos.model.Info
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.viewmodel.viewModelFactory
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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

    val country = countryInfoRepository.country
    val rows = countryInfoRepository.rows



    fun fetchCountryInfoList() {
        fetchCountryInfos {
            countryInfoRepository.getCountryInfos()
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
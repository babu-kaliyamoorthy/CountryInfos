package com.cts.countryinfos.ui

import androidx.lifecycle.viewModelScope
import com.cts.countryinfos.base.CountryInfoBaseViewModel
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.viewmodel.viewModelFactory
import com.cts.countryinfos.utils.RemoteDataNotFoundException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfoListViewModel(private val countryInfoRepository: CountryInfoRepository) :
    CountryInfoBaseViewModel() {


    companion object {

        val FACTORY = viewModelFactory(::CountryInfoListViewModel)
    }

    val country = countryInfoRepository.country

    init {
        fetchCountryInfoList()
    }

    fun fetchCountryInfoList() {
        fetchCountryInfos {
            countryInfoRepository.getCountryInfos()
        }
    }


    fun fetchCountryInfos(block: suspend () -> Unit): Job {

        return viewModelScope.launch {
            try {
//                _spinner.value = true
                block()
            } catch (error: RemoteDataNotFoundException) {
//                _snackBar.value = error.message
            } finally {
//                _spinner.value = false
            }
        }
    }


}
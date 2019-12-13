package com.cts.countryinfos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
fun <T : ViewModel, A> viewModelFactory(constructor: (A) -> T):
            (A) -> ViewModelProvider.NewInstanceFactory {
    return { arg: A ->
        object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <V : ViewModel> create(modelClass: Class<V>): V {
                return constructor(arg) as V
            }
        }
    }
}
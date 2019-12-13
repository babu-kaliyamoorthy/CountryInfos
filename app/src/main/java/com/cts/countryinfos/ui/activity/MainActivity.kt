package com.cts.countryinfos

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.cts.countryinfos.base.CountryInfoBaseActivity
import com.cts.countryinfos.network.CountrtyInfoRemoteDataSourceImpl
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.CountryInfoListViewModel

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
class MainActivity : CountryInfoBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel = ViewModelProviders.of(
            this,
            CountryInfoListViewModel.FACTORY(
                CountryInfoRepository.getInstance(
                    CountrtyInfoRemoteDataSourceImpl.newInstance()
                )
            )
        ).get(CountryInfoListViewModel::class.java)

        viewModel.fetchCountryInfoList()

    }
}

package com.cts.countryinfos

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.countryinfos.base.CountryInfoBaseActivity
import com.cts.countryinfos.databinding.ActivityCountryInfoBinding
import com.cts.countryinfos.model.Info
import com.cts.countryinfos.network.CountrtyInfoRemoteDataSourceImpl
import com.cts.countryinfos.repository.CountryInfoRepository
import com.cts.countryinfos.ui.CountryInfoListViewModel
import com.cts.countryinfos.ui.adapter.CountryInfosListAdapter
import kotlinx.android.synthetic.main.activity_country_info.*

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
class CountryInfoListActivity : CountryInfoBaseActivity() {
    private lateinit var countryInfoListViewModel: CountryInfoListViewModel
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var countryInfosListAdapter: CountryInfosListAdapter
    private lateinit var activityMainBinding: ActivityCountryInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_info)

        initializeIssuesListViewModel()
        initializeIssueListAdapter()

        activityMainBinding.countryInfoViewModel = countryInfoListViewModel
        activityMainBinding.lifecycleOwner = this
        getIssueList()
    }

    private fun initializeIssuesListViewModel() {

        countryInfoListViewModel = ViewModelProviders.of(
            this,
            CountryInfoListViewModel.FACTORY(
                CountryInfoRepository.getInstance(
                    CountrtyInfoRemoteDataSourceImpl.newInstance()
                )
            )
        ).get(CountryInfoListViewModel::class.java)
        countryInfoListViewModel.rows.observe(this, info)


    }

    /**
     * This method is used to do a normal static set up for recyclerview and adapter.
     */
    private fun initializeIssueListAdapter() {
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        countryInfosListAdapter =
            CountryInfosListAdapter(this, countryInfoListViewModel.rows.value ?: emptyList())
        recycler_view.adapter = countryInfosListAdapter
    }

    private fun getIssueList() {
        // if (countryInfoListViewModel != null && countryInfoListViewModel.rows.value?.size == 0) {
        countryInfoListViewModel.fetchCountryInfoList()
        // }
    }

    private val info = Observer<List<Info>> {
        countryInfosListAdapter.update(it)
    }
}

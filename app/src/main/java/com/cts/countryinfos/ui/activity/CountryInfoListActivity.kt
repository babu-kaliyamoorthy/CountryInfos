package com.cts.countryinfos

import android.graphics.Color.WHITE
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.countryinfos.base.CountryInfoBaseActivity
import com.cts.countryinfos.databinding.ActivityCountryInfoBinding
import com.cts.countryinfos.model.Country
import com.cts.countryinfos.network.CountryInfoRemoteDataSourceImpl
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
    private lateinit var activityCountryInfoBinding: ActivityCountryInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityCountryInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_country_info)
        initializeCountryInfoListViewModel()
        initializeCountryInfoListAdapter()
        activityCountryInfoBinding.countryInfoViewModel = countryInfoListViewModel
        activityCountryInfoBinding.lifecycleOwner = this
        getCountryInfo()
        setupPullToRefresh()
    }

    private fun initializeCountryInfoListViewModel() {

        countryInfoListViewModel = ViewModelProviders.of(
            this,
            CountryInfoListViewModel.FACTORY(
                CountryInfoRepository.getInstance(
                    CountryInfoRemoteDataSourceImpl.newInstance()
                )
            )
        ).get(CountryInfoListViewModel::class.java)

        countryInfoListViewModel.country.observe(this, country)
    }

    /**
     * This method is used to do a normal static set up for recyclerview and adapter.
     */
    private fun initializeCountryInfoListAdapter() {
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        countryInfosListAdapter =
            CountryInfosListAdapter(
                this,
                countryInfoListViewModel.country.value?.rows ?: emptyList()
            )
        recycler_view.adapter = countryInfosListAdapter
    }

    private fun getCountryInfo() {
        countryInfoListViewModel.fetchCountryInfoList()
    }


    private val country = Observer<Country> {
        //Update title bar
        supportActionBar?.title = it.title
        // update the list
        it.rows?.let { it1 -> countryInfosListAdapter.update(it1) }
    }

    private fun setupPullToRefresh() {
        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        itemsswipetorefresh.setColorSchemeColors(WHITE)
        itemsswipetorefresh.setOnRefreshListener {
            getCountryInfo()
            itemsswipetorefresh.isRefreshing = false
        }
    }
}
